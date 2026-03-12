<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>GradeV2 - Operateurs</title>
    <link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
    <jsp:include page="common/navbar.jsp" />

    <div class="container">
        <div style="display: grid; grid-template-columns: 2fr 1fr; gap: 2rem;">
            <div class="glass-card">
                <h2>Operateurs de calcul</h2>
                <div class="table-container">
                    <table>
                        <thead>
                            <tr>
                                <th>Nom</th>
                                <th>Symbole</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${operateurs}" var="op">
                                <tr>
                                    <td style="font-weight: 600;">${op.nom}</td>
                                    <td><span class="badge blue" style="font-family: monospace; font-size: 1.1rem;">${op.symbole}</span></td>
                                    <td style="display: flex; gap: 0.5rem;">
                                        <a href="/admin/operateurs/edit/${op.id}" class="btn btn-outline" style="padding: 0.4rem 0.8rem; font-size: 0.75rem; color: var(--accent);">Modifier</a>
                                        <a href="/admin/operateurs/delete/${op.id}" class="btn btn-outline" style="padding: 0.4rem 0.8rem; font-size: 0.75rem; color: var(--danger);">Supprimer</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="glass-card">
                <h2>${editOperateur != null ? 'Modifier' : 'Nouvel'} Operateur</h2>
                <form action="/admin/operateurs/save" method="POST">
                    <input type="hidden" name="id" value="${editOperateur.id}">
                    <div class="form-group">
                        <label>Nom de l'operation</label>
                        <input type="text" name="nom" placeholder="Ex: Addition" value="${editOperateur.nom}" required>
                    </div>
                    <div class="form-group">
                        <label>Symbole / Mot-clef</label>
                        <input type="text" name="symbole" placeholder="Ex: +, -, AVG, MAX" value="${editOperateur.symbole}" required>
                    </div>
                    <button type="submit" class="btn btn-primary" style="width: 100%;">${editOperateur != null ? 'Mettre a jour' : 'Enregistrer'}</button>
                    <c:if test="${editOperateur != null}">
                        <a href="/admin/operateurs" class="btn btn-outline" style="width: 100%; margin-top: 0.5rem; text-align: center; display: block;">Annuler</a>
                    </c:if>
                </form>
                <div style="margin-top: 2rem; font-size: 0.8rem; color: var(--text-secondary);">
                    <p>💡 Utilisez <strong>MIN</strong>, <strong>MAX</strong>, ou <strong>AVG</strong> pour les strategies de resolution dynamique.</p>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
