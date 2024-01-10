<style scoped>
.el-menu-item{
  color:gold;
}
.hide-scrollbar::-webkit-scrollbar-thumb {
  background-color: transparent;
}
</style>
<template>
  <div>
    <el-container  >
      <el-aside width="sideWidth" style="background-color: rgb(238, 241, 246);min-height: 500vh">
          <el-menu :default-openeds="['1', '2']" background-color="rgb(48,65,86)" text-color="#fff" style="min-height: 500vh"
                   :collapse="isCollapse"
                   @select="handleMenuClick"
          >
            <div style="height: 60px;text-align: center;line-height: 60px;color: gold;font-size: 30px">
              {{watcher}}
            </div>
            <el-submenu index="1" >
              <template slot="title"><i class="el-icon-film"></i>
                <span slot="title">电影推荐</span>
              </template>

              <el-menu-item index="1-1">当前热映</el-menu-item>
              <el-menu-item index="1-2">每周热映</el-menu-item>
              <el-menu-item index="1-3">每月热映</el-menu-item>

              <el-menu-item index="1-4">评分最高</el-menu-item>

            </el-submenu>
            <el-submenu index="2">
              <template slot="title"><i class="el-icon-s-shop"></i>
                <span slot="title">电影分类</span>
              </template>
              <el-menu-item index="2-1">动画</el-menu-item>
              <el-menu-item index="2-2">科幻</el-menu-item>
              <el-menu-item index="2-3">悬疑</el-menu-item>
              <el-menu-item index="2-4">爱情</el-menu-item>
              <el-menu-item index="2-5">喜剧</el-menu-item>
            </el-submenu>
            <el-submenu index="3">
              <template slot="title"><i class="el-icon-s-shop"></i>
                <span slot="title">会员充值</span>
              </template>
            </el-submenu>
          </el-menu>


      </el-aside>

      <el-container>
        <el-header style="font-size: 12px;border-bottom: 1px solid #ccc;text-align: right;display: flex;line-height: 60px">
          <div style="flex:1;font-size:18px;text-align: left">
            <span :class="collapseBtnClass" style="cursor: pointer;margin-right: 15px" @click="collapse"></span>
            <b>{{mainTitle}}</b>/{{subTitle}}
          </div>
          <span style="margin-right: 6px;font-size: 16px">{{user.name}}</span>
          <el-dropdown style="cursor: pointer">
            <i class="el-icon-arrow-down" style="margin-right: 15px"></i>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>个人信息</el-dropdown-item>
              <el-dropdown-item  @click.native="logout">退出</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>

        </el-header>

        <el-main>

          <div style="padding:10px 0">
            <el-input style="width: 200px" placeholder="请输入关键字" suffix-icon="el-icon-search"  v-model="search"></el-input>
            <el-button style="margin-left: 5px" type="primary" @click="handleSearch">搜索</el-button>
          </div>


          <el-table :data="tableData">
            <el-table-column prop="name" label="电影" width="150">
            </el-table-column>
            <el-table-column prop="actors" label="主演" width="150">
            </el-table-column>
            <el-table-column prop="categories" label="类别" width="150">
            </el-table-column>
            <el-table-column prop="count" label="播放次数" width="150">
            </el-table-column>
            <el-table-column prop="score" label="评分" width="150">
            </el-table-column>
            <el-table-column>
              <template slot-scope="scope">
                <el-button type="primary" style="background-color: #42b983" @click="play(scope.row)">播放<i class="el-icon-video-play" style="margin-left: 5px"></i></el-button>
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
import Aside from '@/components/Aside.vue'

export default {
  name: 'HomeView',
  components: {
    HelloWorld,
    Aside
  },
  data(){
    return {
      user:'',
      tableData:[],
      total:1,
      collapseBtnClass:'el-icon-s-fold',
      isCollapse:false,
      sideWith:200,
      watcher:"Watcher",
      pageNum:1,
      pageSize:2,
      search:'',
      category:'',
      order:'score',
      subTitle:'当前热映',
      mainTitle:'电影',
      mainArray:['电影','分类','会员'],
      subArray:[['当前热映','每周热映','每月热映','评分最高'],
                ['动画','科幻','悬疑','爱情','喜剧']]
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
      this.user=JSON.parse(window.localStorage.getItem('access'))
      console.log(this.user)
      if(this.user==null){
        this.$router.replace('/login')
      }
      //请求分页查询数据
      fetch("http://localhost:8079/page?pageNum="+this.pageNum+"&pageSize="+this.pageSize+"&search="+this.search+"&order="+this.order).then(res=>{
        console.log("finding page...")
        console.log(res)
        return res.json()
      }).then(jsonData=>{
        console.log(jsonData)
        this.total=jsonData.total
        this.tableData=jsonData.records
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
    },
    handleSearch(){
      this.load()
    },
    handleMenuClick(index){
      console.log("now:"+index);
      this.mainTitle=this.mainArray[Number(index[0]-1)]
      this.subTitle=this.subArray[Number(index[0]-1)][Number(index[2]-1)]
      if(index<="1-3"){
          this.order='count';
          this.load()
      }
      else if(index=="1-4"){
        this.order='score';
        this.load()
      }
      else if(index[0]=='2'){
        fetch("http://localhost:8079/category?pageNum="+this.pageNum+"&pageSize="+this.pageSize+"&search="+this.search+"&category="+this.subTitle).then(res=>{
          console.log("finding page...")
          console.log(res)
          return res.json()
        }).then(jsonData=>{
          console.log(jsonData)
          this.total=jsonData.total
          this.tableData=jsonData.records
        })
      }
      else{

      }
    },
    play(row){
      console.log('Play:'+row.name)
      fetch("http://localhost:8079/play?name="+row.name+"&token="+this.user.token).then(res=>{
       // console.log(res.json())
        return res.json()
      }).then(jsonData=>{
        if(jsonData.msg=='播放成功'){
          window.open('https://search.bilibili.com/all?keyword='+row.name,'_blank')
        }
        else{
          alert("缺少权限！")
        }
      })
      this.load()
    },
    logout(){
      console.log("logout...")
      window.localStorage.setItem('access',null)
      this.load()
    }
  }
}
</script>
