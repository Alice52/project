<template>
  <div>
    <el-button type="danger"
               round
               @click='batchDelete'>批量删除</el-button>
    <el-tree :data='menus'
             :props='defaultProps'
             :show-checkbox='true'
             :draggable='true'
             node-key='catId'
             :default-expanded-keys='expandedKeys'
             :expand-on-click-node='true'
             ref="menuTree">

      <span class='custom-tree-node'
            slot-scope='{ node, data }'>
        <span>{{ node.label }}</span>
        <span>
          <el-button v-if='node.level<=2'
                     type='text'
                     size='mini'
                     @click.stop='() => append(data)'>
            添加
          </el-button>
          <el-button type='text'
                     size='mini'
                     @click.stop='edit(data)'>
            修改
          </el-button>
          <el-button v-if='node.isLeaf == true'
                     type='text'
                     size='mini'
                     @click.stop='() => remove(node, data)'>
            刪除
          </el-button>
        </span>
      </span>
    </el-tree>

    <el-dialog :title='title'
               :close-on-click-modal='false'
               :visible.sync='dialogVisible'
               width='30%'>
      <el-form :model='category'>
        <el-form-item label='分类名称'>
          <el-input v-model='category.name'
                    autocomplete='off'></el-input>
        </el-form-item>
        <el-form-item label='图标'>
          <el-input v-model='category.icon'
                    autocomplete='off'></el-input>
        </el-form-item>
        <el-form-item label='计量单位'>
          <el-input v-model='category.productUnit'
                    autocomplete='off'></el-input>
        </el-form-item>
      </el-form>
      <span slot='footer'
            class='dialog-footer'>
        <el-button @click='dialogVisible = false'>取 消</el-button>
        <el-button type='primary'
                   @click='submitData'>确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data () {
    return {
      maxLevel: 0,
      menus: [],
      expandedKeys: [],
      title: '',
      dialogType: '',
      dialogVisible: false,
      defaultProps: {
        children: 'children',
        label: 'name'
      },
      category: {
        name: '',
        parentCid: 0,
        catLevel: 0,
        showStatus: 1,
        sort: 0,
        icon: '',
        productUnit: ''
      }
    }
  },
  props: {
    prop: {
      type: String,
      required: false
    }
  },
  components: {},
  computed: {},
  watch: {},
  methods: {
    batchDelete () {
      let catIds = []
      let checkedNodes = this.$refs.menuTree.getCheckedNodes()
      console.log('被选中的元素', checkedNodes)
      catIds = checkedNodes.map(x => x.catId)
      this.$confirm(`是否批量删除【${checkedNodes.map(x => x.name)}】菜单?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          this.$http({
            url: this.$http.adornUrl('/product/category/delete'),
            method: 'delete',
            data: this.$http.adornData(catIds, false)
          }).then(({ data }) => {
            this.$message({
              message: '菜单批量删除成功',
              type: 'success'
            })
            this.getMenus()
          })
        })
        .catch(() => { })
    },
    getMenus () {
      this.$http({
        url: this.$http.adornUrl('/product/category/list/tree'),
        method: 'get',
        params: this.$http.adornParams({})
      }).then(({ data }) => {
        console.log('success', data)
        this.menus = data.data
      })
    },
    submitData () {
      if (this.dialogType === 'add') {
        this.addCategory()
      } else {
        this.editCategory()
      }
    },
    editCategory () {
      this.dialogVisible = true
      let { name, icon, productUnit } = this.category
      this.$http({
        url: this.$http.adornUrl(`/product/category/update/${this.category.catId}`),
        data: this.$http.adornData({ name, icon, productUnit }, false),
        method: 'put'
      }).then(({ data }) => {
        this.dialogVisible = false
        this.$message({
          type: 'success',
          message: '修改成功!'
        })
        this.getMenus()
        this.expandedKeys = [this.category.parentCid]
      })
    },
    addCategory () {
      this.dialogVisible = true
      console.log('submit data', this.category)
      this.$http({
        url: this.$http.adornUrl('/product/category/save'),
        method: 'post',
        data: this.$http.adornData(this.category, false)
      }).then(({ data }) => {
        console.log('save success')
        this.dialogVisible = false
        this.$message({
          type: 'success',
          message: '添加成功!'
        })

        this.getMenus()
        this.expandedKeys = [this.category.parentCid]
      })
    },
    edit (data) {
      console.log('update data', this.category)
      this.dialogVisible = true
      this.dialogType = 'edit'
      this.title = '修改分类'

      this.$http({
        url: this.$http.adornUrl(`/product/category/info/${data.catId}`),
        method: 'get'
      }).then(({ data }) => {
        this.category.name = data.category.name
        this.category.catId = data.category.catId
        this.category.productUnit = data.category.productUnit
        this.category.icon = data.category.icon
        this.category.parentCid = data.category.parentCid
      })
    },
    append (data) {
      this.category = {
        name: '',
        parentCid: 0,
        catLevel: 0,
        showStatus: 1,
        sort: 0,
        icon: '',
        productUnit: ''
      }
      this.dialogType = 'add'
      this.dialogVisible = true
      this.title = '添加分类'
      this.category.parentCid = data.catId
      this.category.catLevel = data.catLevel * 1 + 1
    },

    remove (node, data) {
      console.log(node, data)
      let deleteIds = [data.catId]

      // delete confirm
      this.$confirm(`是否删除 ${data.name}`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http({
          url: this.$http.adornUrl('/product/category/delete'),
          method: 'delete',
          data: this.$http.adornData(deleteIds, false)
        }).then(({ data }) => {
          console.log('delete success')
          this.$message({
            type: 'success',
            message: '删除成功!'
          })
          this.getMenus()
          this.expandedKeys = [node.parent.data.catId]
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    }
  },
  beforeCreate () { },
  created () {
    this.getMenus()
  },
  beforeMount () { },
  mounted () { },
  beforeUpdate () { },
  updated () { },
  beforeDestroy () { },
  destroyed () { },
  // brower has keep-alive will trigger this lifecycle
  activated () { },
  filters: {}
}
</script>

<style></style>
