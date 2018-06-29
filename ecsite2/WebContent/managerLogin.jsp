<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="Content-Script-Type" content="text/javascript" />
<meta http-equiv="imagetoolbar" content="no" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<title>管理者Login画面</title>

<style type="text/css">
  body{
  margin:0;
  padding:0;
  line-height:1.6;
  letter-spacing:1px;
  font-family:Verdana,Helvetica,sans-serif;
  font-size:12px;
  color:#333;
  background:#fff;
  }

  table{
  text-align:center;
  margin:0 auto;
  }

  #top{
  width:780px;
  margin:30px auto;
  border:1px solid #333;
  }

#header{
  width:100%;
  height:80px;
  background-image:linear-gradient(pink,white);
}

  #main{
  width:100%;
  height:500px;
  text-align:center;
  }

  #footer{
  width:100%;
  height:80px;
  background-image:linear-gradient(white,pink);
  clear:both;
  }

</style>
</head>
<body>

  <div id="header">
    <div id="pr">
    </div>
  </div>
  <div id="main">
    <div id="top">
      <p>管理者Login</p>
    </div>
    <div>
      <h3>管理者IDとパスワードを入力してください</h3>
        <s:form action="ManagerLoginAction">
          <s:textfield name="loginUserId" placeholder="管理者ID"/>
          <s:password name="loginPassword" placeholder="パスワード"/>
          <s:submit value="ログイン"/>
        </s:form>
        <br/>
        <div id="text-link">
        <p>Homeへ戻る場合は<a href='<s:url action="GoHomeAction"/>'>こちら</a></p>
        </div>
      </div>
  </div>
  <div id="footer">
    <div id="pr">
    </div>
  </div>

</body>
</html>