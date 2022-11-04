<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title>Login</title>
  <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
<!-- partial:index.partial.html -->
<div class="login-page">
  <div class="form">
    <form class="login-form" action="loginsubmit" method="post" modelAttribute="user">
      <input type="text" placeholder="Email Address" name="email"/>
      <input type="password" placeholder="password" name="password"/>
      <button>login</button>
      <p class="message">Not registered? <a href="registration">Create an account</a></p>
    </form>
  </div>
</div>

</body>
</html>