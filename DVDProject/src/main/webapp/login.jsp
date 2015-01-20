<%-- 
    Document   : login
    Created on : 09.06.2012, 10:10:04
    Author     : Игорь
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Авторизация пользователя</title>
    </head>
    <body>
        <form method="POST" action="j_security_check">
            <table>
                <tr>
                    <td>Логин</td>
                    <td><input type="text" name="j_username"></td>
                </tr>
                <tr>
                    <td>Пароль</td>
                    <td><input type="password" name="j_password"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Войти"</td>
                </tr>
            </table>
        </form>
    </body>
</html>
