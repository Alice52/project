<template>
  <el-row :gutter="20">
    <el-col :span="6">
      <category @tree-node-click='treeNodeClick'></category>
    </el-col>
    <el-col :span="18">
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
            <el-button type="success"
                       @click="getAllDataList()">查询全部</el-button>
            <el-button v-if="isAuth('product:attrgroup:save')"
                       type="primary"
                       @click="addOrUpdateHandle()">新增</el-button>
            <el-button v-if="isAuth('product:attrgroup:delete')"
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
          <el-table-column prop="attrGroupName"
                           header-align="center"
                           align="center"
                           label="组名">
          </el-table-column>
          <el-table-column prop="sort"
                           header-align="center"
                           align="center"
                           label="排序">
          </el-table-column>
          <el-table-column prop="descript"
                           header-align="center"
                           align="center"
                           label="描述">
          </el-table-column>
          <el-table-column prop="icon"
                           header-align="center"
                           align="center"
                           label="组图标">
            <template slot-scope="scope">
              <img :src="scope.row.icon"
                   style="width: 80px; height: 50px" />
            </template>
          </el-table-column>
          <el-table-column prop="catelogId"
                           header-align="center"
                           align="center"
                           label="所属分类">
          </el-table-column>
          <el-table-column prop="createdDate"
                           header-align="center"
                           align="center"
                           label="创建时间">
          </el-table-column>
          <el-table-column fixed="right"
                           header-align="center"
                           align="center"
                           width="150"
                           label="操作">
            <template slot-scope="scope">
              <el-button type="text"
                         size="small"
                         @click="relationHandle(scope.row.attrGroupId)">关联</el-button>
              <el-button type="text"
                         size="small"
                         @click="addOrUpdateHandle(scope.row.attrGroupId)">修改</el-button>
              <el-button type="text"
                         size="small"
                         @click="deleteHandle(scope.row.attrGroupId)">删除</el-button>
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
        <!-- 弹窗, 新增 / 修改 -->
        <add-or-update v-if="addOrUpdateVisible"
                       ref="addOrUpdate"
                       @refreshDataList="getDataList"></add-or-update>
        <relation-update v-if="relationVisible"
                         ref="relationUpdate"
                         @refreshData="getDataList"></relation-update>

      </div>
    </el-col>
  </el-row>

</template>

<script>
import Category from '../common/category'
import AddOrUpdate from './attrgroup-add-or-update'
import RelationUpdate from './attr-group-relation'

export default {
  data () {
    return {
      catId: 0,
      dataForm: {
        key: ''
      },
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListLoading: false,
      dataListSelections: [],
      addOrUpdateVisible: false,
      relationVisible: false
    }
  },
  props: {
    prop: {
      type: String,
      required: false
    }
  },
  components: {
    Category,
    AddOrUpdate,
    RelationUpdate
  },
  computed: {},
  watch: {},
  methods: {
    relationHandle (groupId) {
      this.relationVisible = true
      this.$nextTick(() => {
        this.$refs.relationUpdate.init(groupId)
      })
    },
    getAllDataList () {
      this.getGroupData()
    },
    treeNodeClick (data, node, component) {
      this.dataForm.key = ''
      this.pageIndex = 1
      this.pageSize = 10
      if (data.catLevel === 3) {
        this.catId = data.catId
        this.getDataList()
      }
    },
    // 获取数据列表
    getDataList () {
      console.log(this.catId)
      this.getGroupData(this.catId)
    },
    getGroupData (catId = 0) {
      console.log(this.catId)
      this.dataListLoading = true
      this.$http({
        url: this.$http.adornUrl(`/product/attr-groups/${catId}`),
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
    addOrUpdateHandle (id) {
      this.addOrUpdateVisible = true
      this.$nextTick(() => {
        this.$refs.addOrUpdate.init(id)
      })
    },
    // 删除
    deleteHandle (id) {
      var ids = id ? [id] : this.dataListSelections.map(item => {
        return item.attrGroupId
      })
      this.$confirm(`确定对[id=${ids.join(',')}]进行[${id ? '删除' : '批量删除'}]操作?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: this.$http.adornUrl('/product/attr-group'),
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
      })
    }
  },
  beforeCreate () { },
  created () { },
  beforeMount () { },
  mounted () { },
  beforeUpdate () { },
  updated () { },
  beforeDestroy () { },
  destroyed () { },
  // brower has keep-alive will trigger this lifecycle
  activated () {
    this.getDataList()
  },
  filters: {}
}
</script>

<style></style>
