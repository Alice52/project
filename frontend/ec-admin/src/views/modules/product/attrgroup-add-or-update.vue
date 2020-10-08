<template>
  <el-dialog :title="!dataForm.id ? '新增' : '修改'"
             :close-on-click-modal="false"
             :visible.sync="visible">
    <el-form :model="dataForm"
             :rules="dataRule"
             ref="dataForm"
             @keyup.enter.native="dataFormSubmit()"
             label-width="140px">
      <el-form-item label="组名"
                    prop="attrGroupName">
        <el-input v-model="dataForm.attrGroupName"
                  placeholder="组名"></el-input>
      </el-form-item>
      <el-form-item label="排序"
                    prop="sort">
        <el-input v-model.number="dataForm.sort"
                  placeholder="排序"></el-input>
      </el-form-item>
      <el-form-item label="描述"
                    prop="descript">
        <el-input v-model="dataForm.descript"
                  placeholder="描述"></el-input>
      </el-form-item>
      <el-form-item label="组图标"
                    prop="icon">
        <single-upload v-model="dataForm.icon"></single-upload>
      </el-form-item>
      <el-form-item label="所属分类"
                    prop="catelogId">
        <el-cascader v-model="dataForm.catelogPath"
                     :options="categorys"
                     placeholder="试试搜索: 手机"
                     filterable
                     :props="props"></el-cascader>
      </el-form-item>
    </el-form>
    <span slot="footer"
          class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary"
                 @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
import SingleUpload from '@/components/upload/singleUpload'
export default {
  components: {
    SingleUpload
  },
  data () {
    let validateSort = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('排序不能为空'))
      } else if (!Number.isInteger(value)) {
        callback(new Error('请输入数字值'))
      } else {
        callback()
      }
    }
    return {
      props: {
        value: 'catId',
        label: 'name',
        children: 'children'
      },

      categorys: [],
      visible: false,
      dataForm: {
        attrGroupId: 0,
        attrGroupName: '',
        sort: '',
        descript: '',
        icon: '',
        catelogPath: [],
        catelogId: 0
      },
      dataRule: {
        attrGroupName: [
          { required: true, message: '组名不能为空', trigger: 'blur' }
        ],
        sort: [
          { validator: validateSort, trigger: 'blur' }
        ],
        descript: [
          { required: true, message: '描述不能为空', trigger: 'blur' }
        ],
        icon: [
          { required: true, message: '组图标不能为空', trigger: 'blur' }
        ],
        catelogId: [
          { required: true, message: '所属分类不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    getCategorys () {
      this.$http({
        url: this.$http.adornUrl('/product/category/list/tree'),
        method: 'get',
        params: this.$http.adornParams({})
      }).then(({ data }) => {
        console.log('success', data)
        this.categorys = data.data
      })
    },
    init (id) {
      this.dataForm.attrGroupId = id || 0
      this.visible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].resetFields()
        if (this.dataForm.attrGroupId) {
          this.$http({
            url: this.$http.adornUrl(`/product/attr-group/${this.dataForm.attrGroupId}`),
            method: 'get',
            params: this.$http.adornParams()
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.dataForm.attrGroupName = data.attrGroup.attrGroupName
              this.dataForm.sort = data.attrGroup.sort
              this.dataForm.descript = data.attrGroup.descript
              this.dataForm.icon = data.attrGroup.icon
              this.dataForm.catelogId = data.attrGroup.catelogId
              this.dataForm.catelogPath = data.attrGroup.catelogPath
            }
          })
        }
      })
    },
    // 表单提交
    dataFormSubmit () {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          let uri = !this.dataForm.attrGroupId ? '/product/attr-group' : '/product/attr-group/' + this.dataForm.attrGroupId
          this.$http({
            url: this.$http.adornUrl(uri),
            method: !this.dataForm.attrGroupId ? 'post' : 'put',
            data: this.$http.adornData({
              'attrGroupId': this.dataForm.attrGroupId || undefined,
              'attrGroupName': this.dataForm.attrGroupName,
              'sort': this.dataForm.sort,
              'descript': this.dataForm.descript,
              'icon': this.dataForm.icon,
              'catelogId': this.dataForm.catelogPath[this.dataForm.catelogPath.length - 1]
            })
          }).then(({ data }) => {
            if (data && data.code === 0) {
              this.$emit('refreshDataList')
              this.$message({
                message: '操作成功',
                type: 'success',
                duration: 1500,
                onClose: () => {
                  this.visible = false
                  this.dataForm.catelogPath = []
                }
              })
            } else {
              this.$message.error(data.msg)
            }
          })
        }
      })
    }
  },
  created () {
    this.getCategorys()
  }
}
</script>
