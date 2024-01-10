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

<script>
export default {
  name:"Film",
  data(){
    return{
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
  methods:{
    load(){
      //请求分页查询数据
      fetch("http://localhost:8079/page?pageNum="+this.pageNum+"&pageSize="+this.pageSize+"&search"+this.search).then(res=>{
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
    }
  }
}
</script>