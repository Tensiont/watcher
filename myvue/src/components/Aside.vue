<template>
  <el-menu :default-openeds="['1', '3']" background-color="rgb(48,65,86)" text-color="#fff" style="overflow-x: hidden;min-height: 100%"
           :collapse="isCollapse"
  >
    <div style="height: 60px;text-align: center;line-height: 60px;color: gold;font-size: 30px">
      {{watcher}}
    </div>
    <el-submenu index="1">
      <template slot="title"><i class="el-icon-film"></i>
        <span slot="title">电影推荐</span>
      </template>

        <el-menu-item index="1-1">当前热映</el-menu-item>
        <el-menu-item index="1-2">每周热映</el-menu-item>
        <el-menu-item index="1-3">每月热映</el-menu-item>

        <el-menu-item index="1-4">评分最高</el-menu-item>
      <el-submenu index="1-5">
        <template slot="title">分类</template>
        <el-menu-item index="1-5-1">动画</el-menu-item>
        <el-menu-item idex="1-5-2">科幻</el-menu-item>
        <el-menu-item index="1-5-3">悬疑</el-menu-item>
        <el-menu-item index="1-5-4">爱情</el-menu-item>
        <el-menu-item index="1-5-5">喜剧</el-menu-item>
      </el-submenu>
    </el-submenu>
    <el-submenu index="2">
      <template slot="title"><i class="el-icon-s-shop"></i>
        <span slot="title">会员充值</span>
      </template>
      <el-menu-item-group>
        <template slot="title">分组一</template>
        <el-menu-item index="2-1">选项1</el-menu-item>
        <el-menu-item index="2-2">选项2</el-menu-item>
      </el-menu-item-group>
      <el-menu-item-group title="分组2">
        <el-menu-item index="2-3">选项3</el-menu-item>
      </el-menu-item-group>
      <el-submenu index="2-4">
        <template slot="title">选项4</template>
        <el-menu-item index="2-4-1">选项4-1</el-menu-item>
      </el-submenu>
    </el-submenu>
    <el-submenu index="3">
      <template slot="title"><i class="el-icon-setting"></i>
        <span slot="title">导航一</span>
      </template>
      <el-menu-item-group>
        <template slot="title">分组一</template>
        <el-menu-item index="3-1">选项1</el-menu-item>
        <el-menu-item index="3-2">选项2</el-menu-item>
      </el-menu-item-group>
      <el-menu-item-group title="分组2">
        <el-menu-item index="3-3">选项3</el-menu-item>
      </el-menu-item-group>
      <el-submenu index="3-4">
        <template slot="title">选项4</template>
        <el-menu-item index="3-4-1">选项4-1</el-menu-item>
      </el-submenu>
    </el-submenu>
  </el-menu>
</template>

<script>
export default {
  name:"Aside",
  props:{
    isCollapse:Boolean,
    watcher:String
  }
}
</script>