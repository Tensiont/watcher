<template>
  <div class="login-page">
    <el-tabs
        v-model="activeName"
        @tab-click="handleClick"
    >
      <el-tab-pane label="用户登录" name="first">
        <el-form ref="form" :model="form" class="login-form">
          <el-form-item label="邮箱" prop="email">
            <el-input v-model="form.email"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input v-model="form.password" type="password"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="login">登录</el-button>
          </el-form-item>
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="用户注册" name="second">
        <el-form ref="form2" :model="form2" class="login-form">
          <el-form-item label="用户名" prop="name">
            <el-input v-model="form2.name"></el-input>
          </el-form-item>
          <el-form-item label="邮箱" prop="email">
            <el-input v-model="form2.email"></el-input>
          </el-form-item>
          <el-form-item label="验证码" prop="code">
            <el-input v-model="form2.code"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input v-model="form2.password" type="password"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="registerUser">注册</el-button>
          </el-form-item>
        </el-form>

      </el-tab-pane>
    </el-tabs>
  </div>
</template>
<script>

export default {
  data() {
    return {
      activeName: 'first',
      form:{
        password:'',
        email:''
      },
      form2:{
        name:'',
        email:'',
        code:'验证码恒为123456',
        password: ''
      }
    };
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event);
    },
    registerUser(){
      fetch("http://localhost:8079/register?name="+this.form2.name+"&email="+this.form2.email+"&password="+this.form2.password+"&code="+this.form2.code).then(res=>{
        console.log(res)
        return res.text()
      }).then(text=>{

        console.log(text)
        alert(text)
      })
    },
    login(){
      fetch("http://localhost:8079/login?email="+this.form.email+"&password="+this.form.password).then(res=>{
        console.log(res)
        return res.json()
      }).then(jsonData=>{
        if(jsonData==null){
          alert("账号或密码错误")
          return
        }
        console.log(jsonData)
        localStorage.setItem('access',JSON.stringify(jsonData))
        this.$router.replace({path:'/'})
      })
    }
  }
};
</script>
<style>
.login-page {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.login-form {
  /*widows: 300px;*/
  border: 1px solid #eee;
  padding: 40px 80px 20px 80px;
  border-radius: 10px;
}
</style>