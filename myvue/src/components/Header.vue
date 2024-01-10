<template>
  <el-header style="font-size: 12px;text-align: right;display: flex;line-height: 60px">
    <div style="flex:1;font-size:18px;text-align: left">
      <span :class="collapseBtnClass" style="cursor: pointer" @click="collapse"></span>
    </div>
    <span style="margin-right: 6px;font-size: 16px">Admin</span>
    <el-dropdown style="cursor: pointer">
      <i class="el-icon-arrow-down" style="margin-right: 15px"></i>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item>个人信息</el-dropdown-item>
        <el-dropdown-item>退出</el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>

  </el-header>
</template>

<script>
export default {
  name:"Header",
  props:{
    collapseBtnClass:String,
    isCollapse:Boolean
  },
  created(){
    this.load()
  },
  methods: {
    collapse() {
      this.isCollapse = !this.isCollapse
      if (this.isCollapse) {
        this.sideWith = 200
        this.collapseBtnClass = 'el-icon-s-unfold'
        this.watcher = "W"
      } else {
        this.sideWith = 50
        this.collapseBtnClass = 'el-icon-s-fold'
        this.watcher = "Watcher"
      }
    }
  }
}
</script>