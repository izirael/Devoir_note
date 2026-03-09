<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Candidate Management</title>
    <link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>Gestion des Candidats</h1>
            <nav>
                <a href="/admin/parametres" class="nav-link">Barèmes</a>
                <a href="/admin/operateurs" class="nav-link">Opérateurs</a>
                <a href="/simulate" class="nav-link" style="color: var(--accent)">Simuler</a>
            </nav>
        </header>

        <div class="glass-card" style="margin-bottom: 2rem;">
            <h2>Liste des Candidats</h2>
            <table>
                <thead>
                    <tr>
                        <th>Matricule</th>
                        <th>Nom</th>
                        <th>Prénom</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${candidats}" var="c">
                        <tr>
                            <td>${c.matricule}</td>
                            <td>${c.nom}</td>
                            <td>${c.prenom}</td>
                            <td>
                                <a href="/candidat/${c.id}" class="nav-link">Voir Notes</a> |
                                <a href="/candidat/delete/${c.id}" class="nav-link" style="color: var(--danger)" onclick="return confirm('Supprimer ce candidat ?')">Supprimer</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="glass-card" style="max-width: 500px;">
            <h2>Ajouter / Modifier Candidat</h2>
            <form action="/candidat/save" method="POST">
                <input type="hidden" name="id" id="candId">
                <div class="form-group">
                    <label>Matricule</label>
                    <input type="text" name="matricule" required>
                </div>
                <div class="form-group">
                    <label>Nom</label>
                    <input type="text" name="nom" required>
                </div>
                <div class="form-group">
                    <label>Prénom</label>
                    <input type="text" name="prenom" required>
                </div>
                <button type="submit">Enregistrer</button>
            </form>
        </div>
    </div>
</body>
</html>
