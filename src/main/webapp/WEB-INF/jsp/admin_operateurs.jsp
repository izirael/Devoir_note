<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Manage Operators - Admin</title>
    <link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>Gestion des Opérateurs</h1>
            <nav>
                <a href="/" class="nav-link">Dashboard</a>
                <a href="/admin/parametres" class="nav-link">Barèmes</a>
            </nav>
        </header>

        <div class="glass-card" style="margin-bottom: 2rem;">
            <h2>Existing Operators</h2>
            <table>
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Symbol</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${operateurs}" var="op">
                        <tr>
                            <td>${op.nom}</td>
                            <td>${op.symbole}</td>
                            <td>
                                <a href="/admin/operateurs/delete/${op.id}" class="nav-link" style="color: var(--danger)">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="glass-card" style="max-width: 500px;">
            <h2>Add Operator</h2>
            <form action="/admin/operateurs/save" method="POST">
                <div class="form-group">
                    <label>Name</label>
                    <input type="text" name="nom" required>
                </div>
                <div class="form-group">
                    <label>Symbol (+, -, *, /)</label>
                    <input type="text" name="symbole" required>
                </div>
                <button type="submit">Add Operator</button>
            </form>
        </div>
    </div>
</body>
</html>
