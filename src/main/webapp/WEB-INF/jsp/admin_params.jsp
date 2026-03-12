<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>GradeV2 - Configurations</title>
    <link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
    <jsp:include page="common/navbar.jsp" />

    <div class="container">
        <div style="display: grid; grid-template-columns: 2fr 1fr; gap: 2rem;">
            <div class="glass-card">
                <h2>Baremes de multi-correction</h2>
                <div class="table-container">
                    <table>
                        <thead>
                            <tr>
                                <th>Matiere</th>
                                <th>Operateur</th>
                                <th>Resolution</th>
                                <th>Seuil (Gap &le;)</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${parametres}" var="p">
                                <tr>
                                    <td style="font-weight: 600;">${p.matiere.nom}</td>
                                    <td><span class="badge blue">${p.operateur.symbole}</span></td>
                                    <td><span class="badge blue">${p.resolution.nom}</span></td>
                                    <td><span style="font-family: monospace; font-size: 1.1rem;">${p.gap}</span></td>
                                    <td style="display: flex; gap: 0.5rem;">
                                        <a href="/admin/parametres/edit/${p.id}" class="btn btn-outline" style="padding: 0.4rem 0.8rem; font-size: 0.75rem; color: var(--accent);">Modifier</a>
                                        <a href="/admin/parametres/delete/${p.id}" class="btn btn-outline" style="padding: 0.4rem 0.8rem; font-size: 0.75rem; color: var(--danger);">Supprimer</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="glass-card">
                <h2>${editParametre != null ? 'Modifier' : 'Nouvelle'} Regle</h2>
                <form action="/admin/parametres/save" method="POST">
                    <input type="hidden" name="id" value="${editParametre.id}">
                    <div class="form-group">
                        <label>Matiere</label>
                        <select name="idMatiere" required>
                            <c:forEach items="${matieres}" var="m">
                                <option value="${m.idMatiere}" ${editParametre.matiere.idMatiere == m.idMatiere ? 'selected' : ''}>${m.nom}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Operateur</label>
                        <select name="idOperateur" required>
                            <c:forEach items="${operateurs}" var="op">
                                <option value="${op.id}" ${editParametre.operateur.id == op.id ? 'selected' : ''}>${op.symbole}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Strategie de Resolution</label>
                        <select name="idResolution" required>
                            <c:forEach items="${resolutions}" var="res">
                                <option value="${res.id}" ${editParametre.resolution.id == res.id ? 'selected' : ''}>${res.nom}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Seuil de Gap Maximum</label>
                        <input type="number" name="gap" placeholder="Ex: 5" value="${editParametre.gap}" required>
                    </div>
                    <button type="submit" class="btn btn-primary" style="width: 100%;">${editParametre != null ? 'Mettre a jour' : 'Ajouter le Bareme'}</button>
                    <c:if test="${editParametre != null}">
                        <a href="/admin/parametres" class="btn btn-outline" style="width: 100%; margin-top: 0.5rem; text-align: center; display: block;">Annuler</a>
                    </c:if>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
