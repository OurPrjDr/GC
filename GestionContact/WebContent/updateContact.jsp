<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>Mise-a-jour Contact</title>
    </head>
    <body>
        <form method="post" action="UpdateContact">
            id: <input type="text" name="id"/><br>
            nouveau prenom: <input type="text" name="nouvPrenom"/><br>
            nouveau nom: <input type="text" name="nouvNom"/><br>
            nouveau email: <input type="text" name="nouvEmail"/><br>        
            <input type="submit" value="Submit"/>
            <input type="reset" value="Reset"/>
        </form>
    </body>
</html>