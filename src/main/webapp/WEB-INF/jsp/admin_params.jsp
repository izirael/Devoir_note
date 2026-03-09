<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Manage Scales - Admin</title>
    <link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>Configuration des Barèmes</h1>
            <nav>
                <a href="/" class="nav-link">Dashboard</a>
                <a href="/admin/operateurs" class="nav-link">Operateurs</a>
            </nav>
        </header>

        <div class="glass-card" style="margin-bottom: 2rem;">
            <h2>Existing Scales</h2>
            <table>
                <thead>
                    <tr>
                        <th>Min Gap</th>
                        <th>Max Gap</th>
                        <th>Operator</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${parametres}" var="p">
                        <tr>
                            <td>${p.min}</td>
                            <td>${p.max}</td>
                            <td>${p.operateur.nom} (${p.operateur.symbole})</td>
                            <td>
                                <a href="/admin/parametres/delete/${p.id}" class="nav-link" style="color: var(--danger)">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="glass-card" style="max-width: 500px;">
            <h2>Add / Edit Scale</h2>
            <form action="/admin/parametres/save" method="POST">
                <div class="form-group">
                    <label>Min Gap</label>
                    <input type="number" name="min" required>
                </div>
                <div class="form-group">
                    <label>Max Gap</label>
                    <input type="number" name="max" required>
                </div>
                <div class="form-group">
                    <label>Operator</label>
                    <select name="idOperateur" required>
                        <c:forEach items="${operateurs}" var="op">
                            <option value="${op.id}">${op.nom} (${op.symbole})</option>
                        </c:forEach>
                    </select>
                </div>
                <button type="submit">Save Scale</button>
            </form>
        </div>
    </div>
</body>
</html>
