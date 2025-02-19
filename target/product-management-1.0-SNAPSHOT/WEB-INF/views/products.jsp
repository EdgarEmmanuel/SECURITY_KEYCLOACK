<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des Produits</title>
</head>
<body>
    <h2>Produits</h2>
    <form action="add" method="post">
        Référence: <input type="text" name="ref" required>
        Nom: <input type="text" name="name" required>
        <button type="submit">Ajouter</button>
    </form>
    <table border="1">
        <tr>
            <th>Référence</th>
            <th>Nom</th>
        </tr>
        <c:forEach var="product" items="${products}">
            <tr>
                <td>${product.ref}</td>
                <td>${product.name}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
