<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>叩丁狼客户管理系统-登录</title>
  <link rel="stylesheet" href="/js/bootstrap/css/bootstrap.css">
  <link rel="stylesheet" href="/js/font-awesome/css/font-awesome.min.css">
  <link rel="stylesheet" href="/js/Ionicons/css/ionicons.min.css">
  <link rel="stylesheet" href="/js/adminlte/css/AdminLTE.min.css">
  <link rel="stylesheet" href="/js/adminlte/css/skins/_all-skins.min.css">
  <link rel="stylesheet" href="/js/adminlte/css/fonts.googleapis.com.css">

  <script src="/js/jquery/jquery.min.js"></script>
  <script src="/js/bootstrap/js/bootstrap.js"></script>
  <script src="/js/adminlte/js/adminlte.min.js"></script>
  <script src="/js/plugins/twbsPagination/jquery.twbsPagination.min.js"></script>
  <script src="/js/system/commonAll.js"></script>
  <script src="/js/jQueryValidation/dist/jquery.validate.js"></script>
<style>
  .form-control1{
    width: 60%;
    /*display: block;*/
    right: 0;
  }
  .form-control-feedback1{
    right: 40%;
  }
  .img-thumbnail{
    margin-left: 15px;
    position:absolute;
    top : 0;
    right: 20px;
  }
  .error{
    color:red;
  }
</style>

</head>
<body class="hold-transition login-page">
<div class="login-box">
  <div class="login-logo">
    <a href="http://www.wolfcode.cn"><b>叩丁狼</b>CRM</a>
  </div>
  <!-- /.login-logo -->
  <div class="login-box-body">
    <p class="login-box-msg">请输入账号密码</p>
    <span style="color: red">${param.f == 1?"验证码输入错误":""}${param.f == 2?"用户名或密码输入错误":""}</span>
    <form  method="post" id="loginForm" action="login">
      <div class="form-group has-feedback">
        <input type="text" class="form-control" placeholder="请输入姓名" id="user" name="name" value="">
        <span class="glyphicon glyphicon-envelope form-control-feedback"></span>

      </div>
      <div class="form-group has-feedback">
        <input type="password" class="form-control" placeholder="请输入密码" id="pass" name="password" value="">
        <span class="glyphicon glyphicon-lock form-control-feedback"></span>

      </div>
      <div class="form-group has-feedback">
        <input type="text" class="form-control form-control1" placeholder="请输入验证码" id="verifycode" name="verifycode" value="">
        <span class="glyphicon glyphicon-lock form-control-feedback form-control-feedback1"></span>
        <img src="/code" id="verifycodeimg" class="img-thumbnail" width="95">

      </div>
      <div class="row">

        <div class="col-xs-12">
          <button type="submit" class="btn btn-primary btn-block btn-flat submitBtn">登录</button>
          <%--<button type="button" class="btn btn-primary btn-block btn-flat" onclick = "location.href='register.jsp'">没有账号,去注册</button>--%>
        </div>
      </div>
    </form>
  </div>
</div>
<script>
  $(function (){
    $("#verifycodeimg").click(function () {
      this.src = "/code?" + new Date().getMilliseconds();
    });
  })
</script>
</body>
</html>
