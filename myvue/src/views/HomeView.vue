<template>
 <div>
   <el-container>
     <el-aside width="sideWidth" style="background-color: rgb(238, 241, 246);min-height: 100%">
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
           <el-menu-item-group>
             <template slot="title">分组一</template>
             <el-menu-item index="1-1">选项1</el-menu-item>
             <el-menu-item index="1-2">选项2</el-menu-item>
           </el-menu-item-group>
           <el-menu-item-group title="分组2">
             <el-menu-item index="1-3">选项3</el-menu-item>
           </el-menu-item-group>
           <el-submenu index="1-4">
             <template slot="title">选项4</template>
             <el-menu-item index="1-4-1">选项4-1</el-menu-item>
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
     </el-aside>

     <el-container>
       <el-header style="font-size: 12px;border-bottom: 1px solid #ccc;text-align: right;display: flex;line-height: 60px">
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

       <el-main>

         <div style="padding:10px 0">
           <el-input style="width: 200px" placeholder="请输入关键字" suffix-icon="el-icon-search"  v-model="search"></el-input>
           <el-button style="margin-left: 5px" type="primary">搜索</el-button>
         </div>


         <el-table :data="tableData">
           <el-table-column prop="name" label="电影" width="200">
           </el-table-column>
           <el-table-column prop="actors" label="主演" width="200">
           </el-table-column>
           <el-table-column prop="categories" label="类别" width="200">
           </el-table-column>
           <el-table-column prop="score" label="评分">
           </el-table-column>
           <el-table-column>
             <template slot-scope="scope">
               <el-button type="primary" style="background-color: #42b983">播放<i class="el-icon-video-play" style="margin-left: 5px"></i></el-button>
             </template>
           </el-table-column>
         </el-table>
         <el-pagination
             @size-change="handleSizeChange"
             @current-change="handleCurrentChange"
             :current-page="pageNum"
             :page-sizes="[2,5,10, 15, 20]"
             :page-size="pageSize"
             layout="total, sizes, prev, pager, next, jumper"
             :total="total">
         </el-pagination>
       </el-main>
     </el-container>
   </el-container>

 </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'HomeView',
  components: {
    HelloWorld
  },
  data(){
    return {
      tableData:[],
      total:1,
      collapseBtnClass:'el-icon-s-fold',
      isCollapse:false,
      sideWith:200,
      watcher:"Watcher",
      pageNum:1,
      pageSize:2,
      search:''
    }
  },
  created(){
    this.load()
  },
  methods:{
    collapse(){
      this.isCollapse=!this.isCollapse
      if(this.isCollapse){
        this.sideWith=200
        this.collapseBtnClass='el-icon-s-unfold'
        this.watcher="W"
      }
      else{
        this.sideWith=50
        this.collapseBtnClass='el-icon-s-fold'
        this.watcher="Watcher"
      }
    },
    load(){
      //请求分页查询数据
      fetch("http://localhost:8079/page?pageNum="+this.pageNum+"&pageSize="+this.pageSize).then(res=>{
        console.log("finding page...")
        console.log(res)
        return res.json()
      }).then(jsonData=>{
        console.log(jsonData)
        this.total=jsonData.total
        this.tableData=jsonData.data
      })
    },
    handleSizeChange(pageSize){
      console.log(pageSize)
      this.pageSize=pageSize
      this.load()
    },
    handleCurrentChange(pageNum){
      console.log(pageNum)
      this.pageNum=pageNum
      this.load()
    }
  }
}
</script>
