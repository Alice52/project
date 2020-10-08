<template>
  <el-tree :data='menus'
           :props='defaultProps'
           @node-click="nodeClick"
           node-key='catId'
           ref="menuTree">
  </el-tree>
</template>

<script>
export default {
  data () {
    return {
      menus: [],
      defaultProps: {
        children: 'children',
        label: 'name'
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
    nodeClick (data, node, component) {
      this.$emit('tree-node-click', data, node, component)
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
