<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>GradeV2 - Gestion Candidats</title>
    <link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
    <jsp:include page="common/navbar.jsp" />

    <div class="container">
        <div style="display: grid; grid-template-columns: 2fr 1fr; gap: 2rem;">
            <div class="glass-card">
                <h2>Liste des Candidats</h2>
                <div class="table-container">
                    <table>
                        <thead>
                            <tr>
                                <th>Matricule</th>
                                <th>Nom & Prenom</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${candidats}" var="c">
                                <tr>
                                    <td><span class="badge blue">${c.matricule}</span></td>
                                    <td style="font-weight: 600;">${c.nom} ${c.prenom}</td>
                                    <td style="display: flex; gap: 0.5rem;">
                                        <a href="/admin/candidats/edit/${c.id}" class="btn btn-outline" style="padding: 0.4rem 0.8rem; font-size: 0.75rem; color: var(--accent);">Modifier</a>
                                        <a href="/admin/candidats/delete/${c.id}" class="btn btn-outline" style="padding: 0.4rem 0.8rem; font-size: 0.75rem; color: var(--danger);">Supprimer</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="glass-card">
                <h2>${editCandidat != null ? 'Modifier' : 'Nouveau'} Candidat</h2>
                <form action="/admin/candidats/save" method="POST">
                    <input type="hidden" name="id" value="${editCandidat.id}">
                    <div class="form-group">
                        <label>Matricule</label>
                        <input type="text" name="matricule" placeholder="Ex: C001" value="${editCandidat.matricule}" required>
                    </div>
                    <div class="form-group">
                        <label>Nom</label>
                        <input type="text" name="nom" value="${editCandidat.nom}" required>
                    </div>
                    <div class="form-group">
                        <label>Prenom</label>
                        <input type="text" name="prenom" value="${editCandidat.prenom}" required>
                    </div>
                    <button type="submit" class="btn btn-primary" style="width: 100%;">
                        ${editCandidat != null ? 'Mettre a jour' : 'Enregistrer'}
                    </button>
                    <c:if test="${editCandidat != null}">
                        <a href="/admin/candidats" class="btn btn-outline" style="width: 100%; margin-top: 0.5rem; text-align: center; display: block;">Annuler</a>
                    </c:if>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
