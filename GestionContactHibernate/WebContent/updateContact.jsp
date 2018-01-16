<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>Mise-a-jour Contact</title>
    </head>
    <body>
        <form method="post" action="UpdateContact">
        	Id du contact à modifier: <input type="text" name="idContact" /><br /> 
            Num Siret: <input type="text" name="numSiret" /><br /> 
            FirstName: <input type="text" name="prenom" /><br /> 
            LastName: <input type="text" name="nom" /><br /> 
            Email: <input type="text" name="email" /><br />
			Street: <input type="text" name="rue" /><br /> 
			City: <input type="text" name="ville" /><br /> 
			Zip: <input type="text" name="code" /><br /> 
			Country: <input type="text" name="pays" /><br /> 
			Mobile Phone: <input type="text" name="telMobile"><br /> 
			House Phone: <input type="text" name="telMaison" /><br /> 
			Office Phone: <input type="text" name="telBureau" /><br /> 
			Friends: <input type="checkbox" name="amis" value="amis" />Amis<br /> 
			Colleagues: <input type="checkbox" name="collegues" value="collegues" />Collegues<br />
			Family: <input type="checkbox" name="famille" value="famille" />Famille<br />
			<input type="button" name="New group" value="nouveauGroupe" /><br />
		      
            <input type="submit" value="Mettre-a-jour le contact"/>
            <input type="reset" value="Annuler"/>
        </form>
    </body>
</html>