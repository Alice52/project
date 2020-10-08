<template>
  <div class="mod-config">
    <el-form :inline="true"
             :model="dataForm"
             @keyup.enter.native="getDataList()">
      <el-form-item>
        <el-input v-model="dataForm.key"
                  placeholder="参数名"
                  clearable></el-input>
      </el-form-item>
      <el-form-item>
        <el-button @click="getDataList()">查询</el-button>
        <el-button v-if="isAuth('product:brand:save')"
                   type="primary"
                   @click="addOrUpdateHandle('add')">新增</el-button>
        <el-button v-if="isAuth('product:brand:delete')"
                   type="danger"
                   @click="deleteHandle()"
                   :disabled="dataListSelections.length <= 0">批量删除</el-button>
      </el-form-item>
    </el-form>
    <el-table :data="dataList"
              border
              v-loading="dataListLoading"
              @selection-change="selectionChangeHandle"
              style="width: 100%;">
      <el-table-column type="selection"
                       header-align="center"
                       align="center"
                       width="50">
      </el-table-column>
      <el-table-column prop="name"
                       header-align="center"
                       align="center"
                       label="品牌名">
      </el-table-column>
      <el-table-column prop="logo"
                       header-align="center"
                       align="center"
                       label="品牌logo">
        <template slot-scope="scope">
          <img :src="scope.row.logo"
               style="width: 80px; height: 50px" />
        </template>

      </el-table-column>
      <el-table-column prop="descript"
                       header-align="center"
                       align="center"
                       label="介绍">
      </el-table-column>
      <el-table-column prop="showStatus"
                       header-align="center"
                       align="center"
                       label="显示状态">
        <template slot-scope="scope">
          <el-switch v-model="scope.row.showStatus"
                     :active-value='1'
                     :inactive-value='0'
                     @change="updateStatus(scope.row)"
                     active-color="#13ce66">
          </el-switch>
        </template>
      </el-table-column>
      <el-table-column prop="firstLetter"
                       header-align="center"
                       align="center"
                       label="检索首字母">
      </el-table-column>
      <el-table-column prop="sort"
                       header-align="center"
                       align="center"
                       label="排序">
      </el-table-column>
      <el-table-column prop="createdDate"
                       header-align="center"
                       align="center"
                       label="创建时间">
      </el-table-column>
      <el-table-column fixed="right"
                       header-align="center"
                       align="center"
                       width="200"
                       label="操作">
        <template slot-scope="scope">
          <el-button type="text"
                     size="small"
                     @click="updateCatelogHandle(scope.row.brandId)">关联分类</el-button>
          <el-button type="text"
                     size="small"
                     @click="addOrUpdateHandle('edit', scope.row.brandId)">修改</el-button>
          <el-button type="text"
                     size="small"
                     @click="deleteHandle(scope.row.brandId, scope.row.name)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination @size-change="sizeChangeHandle"
                   @current-change="currentChangeHandle"
                   :current-page="pageIndex"
                   :page-sizes="[10, 20, 50, 100]"
                   :page-size="pageSize"
                   :total="totalPage"
                   layout="total, sizes, prev, pager, next, jumper">
    </el-pagination>

    <add-or-update v-if="addOrUpdateVisible"
                   ref="addOrUpdate"
                   @refreshDataList="getDataList"></add-or-update>

    <el-dialog title="关联分类"
               :visible.sync="cateRelationDialogVisible"
               width="30%">
      <el-popover placement="right-end"
                  v-model="popCatelogSelectVisible">
        <category-cascader :catelogPath.sync="catelogPath"></category-cascader>
        <div style="text-align: right; margin: 0">
          <el-button size="mini"
                     type="text"
                     @click="popCatelogSelectVisible = false">取消</el-button>
          <el-button type="primary"
                     size="mini"
                     @click="addCatelogSelect">确定</el-button>
        </div>
        <el-button slot="reference">新增关联</el-button>
      </el-popover>
      <el-table :data="cateRelationTableData"
                style="width: 100%">
        <el-table-column prop="brandName"
                         label="品牌名"></el-table-column>
        <el-table-column prop="catelogName"
                         label="分类名"></el-table-column>
        <el-table-column fixed="right"
                         header-align="center"
                         align="center"
                         label="操作">
          <template slot-scope="scope">
            <el-button type="text"
                       size="small"
                       @click="deleteCateRelationHandle(scope.row.id, scope.row.brandId)">移除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <span slot="footer"
            class="dialog-footer">
        <el-button @click="cateRelationDialogVisible = false">取 消</el-button>
        <el-button type="primary"
                   @click="cateRelationDialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import AddOrUpdate from './brand-add-or-update'
import CategoryCascader from '../common/category-cascader'
export default {
  data () {
    return {
      dataForm: {
        key: ''
      },
      brandId: 0,
      catelogPath: [],
      dataList: [],
      cateRelationTableData: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
      dataListSelections: [],
      addOrUpdateVisible: false,
      cateRelationDialogVisible: false,
      popCatelogSelectVisible: false
    }
  },
  components: {
    AddOrUpdate,
    CategoryCascader
  },
  activated () {
    this.getDataList()
  },
  methods: {
    addCatelogSelect () {
      this.popCatelogSelectVisible = false
      this.$http({
        url: this.$http.adornUrl('/product/category-brand-relations'),
        method: 'post',
        data: this.$http.adornData({
          brandId: this.brandId,
          catelogId: this.catelogPath[this.catelogPath.length - 1]
        }, false)
      }).then(({ data }) => {
        this.getCateRelation()
      })
    },
    deleteCateRelationHandle (id, brandId) {
      this.$http({
        url: this.$http.adornUrl(`/product/category-brand-relation/${id}`),
        method: 'delete'
      }).then(({ data }) => {
        this.getCateRelation()
      })
    },
    updateCatelogHandle (brandId) {
      this.cateRelationDialogVisible = true
      this.brandId = brandId
      this.getCateRelation()
    },
    getCateRelation () {
      this.$http({
        url: this.$http.adornUrl(`/product/attr-attrgroup-relations/${this.brandId}`),
        method: 'get'
      }).then(({ data }) => {
        this.cateRelationTableData = data.relations
      })
    },
    updateStatus (brand) {
      console.log(brand)
      this.$http({
        url: this.$http.adornUrl(`/product/brand/${brand.brandId}`),
        method: 'put',
        data: this.$http.adornData({
          'showStatus': brand.showStatus
        }, false)
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.$message({
            message: '修改状态成功',
            type: 'success',
            duration: 1500
            // this.getDataList()
          })
        } else {
          this.$message.error(data.msg)
        }
      })
    },
    // 获取数据列表
    getDataList () {
      this.dataListLoading = true
      this.$http({
        url: this.$http.adornUrl('/product/brands'),
        method: 'get',
        params: this.$http.adornParams({
          'page': this.pageIndex,
          'limit': this.pageSize,
          'key': this.dataForm.key
        })
      }).then(({ data }) => {
        if (data && data.code === 0) {
          this.dataList = data.page.list
          this.totalPage = data.page.totalCount
        } else {
          this.dataList = []
          this.totalPage = 0
        }
        this.dataListLoading = false
      })
    },
    // 每页数
    sizeChangeHandle (val) {
      this.pageSize = val
      this.pageIndex = 1
      this.getDataList()
    },
    // 当前页
    currentChangeHandle (val) {
      this.pageIndex = val
      this.getDataList()
    },
    // 多选
    selectionChangeHandle (val) {
      this.dataListSelections = val
    },
    // 新增 / 修改
    addOrUpdateHandle (type, id) {
      this.addOrUpdateVisible = true
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(type, id)
      })
    },
    // 删除
    deleteHandle (id, name) {
      let ids = id ? [id] : this.dataListSelections.map(item => {
        return item.brandId
      })
      let brandNames = name ? [name] : this.dataListSelections.map(item => {
        return item.name
      })
      this.$confirm(`确定对[${brandNames.join(',')}]进行删除操作?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: this.$http.adornUrl('/product/brands'),
          method: 'delete',
          data: this.$http.adornData(ids, false)
        }).then(({ data }) => {
          if (data && data.code === 0) {
            this.getDataList()
            this.$message({
              message: '操作成功',
              type: 'success',
              duration: 1500,
              onClose: () => { }
            })
          } else {
            this.$message.error(data.msg)
          }
        })
      }).catch(e => { })
    }
  }
}
</script>
