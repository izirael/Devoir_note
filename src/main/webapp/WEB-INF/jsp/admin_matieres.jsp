<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <title>GradeV2 - Matieres</title>
            <link rel="stylesheet" href="/static/css/style.css">
        </head>

        <body>
            <jsp:include page="common/navbar.jsp" />

            <div class="container">
                <div style="display: grid; grid-template-columns: 2fr 1fr; gap: 2rem;">
                    <div class="glass-card">
                        <h2>Matières</h2>
                        <div class="table-container">
                            <table>
                                <thead>
                                    <tr>
                                        <th>Nom</th>
                                        <th>Coefficient</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${matieres}" var="m">
                                        <tr>
                                            <td style="font-weight: 600;">${m.nom}</td>
                                            <td><span class="badge blue"
                                                    style="font-family: monospace; font-size: 1.1rem;">${m.coefficient}</span>
                                            </td>
                                            <td style="display: flex; gap: 0.5rem;">
                                                <a href="/admin/matieres/edit/${m.idMatiere}" class="btn btn-outline"
                                                    style="padding: 0.4rem 0.8rem; font-size: 0.75rem; color: var(--accent);">Modifier</a>
                                                <a href="/admin/matieres/delete/${m.idMatiere}" class="btn btn-outline"
                                                    style="padding: 0.4rem 0.8rem; font-size: 0.75rem; color: var(--danger);">Supprimer</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>

                    <div class="glass-card">
                        <h2>${editMatiere != null ? 'Modifier' : 'Nouvelle'} Matière</h2>
                        <form action="/admin/matieres/save" method="POST">
                            <input type="hidden" name="idMatiere" value="${editMatiere.idMatiere}">
                            <div class="form-group">
                                <label>Nom de la matière</label>
                                <input type="text" name="nom" placeholder="Ex: Informatique" value="${editMatiere.nom}"
                                    required>
                            </div>
                            <div class="form-group">
                                <label>Coefficient</label>
                                <input type="number" step="0.01" name="coefficient" placeholder="Ex: 2.00"
                                    value="${editMatiere.coefficient != null ? editMatiere.coefficient : 1.0}" required>
                            </div>
                            <button type="submit" class="btn btn-primary" style="width: 100%;">${editMatiere != null ?
                                'Mettre à jour' : 'Enregistrer'}</button>
                            <c:if test="${editMatiere != null}">
                                <a href="/admin/matieres" class="btn btn-outline"
                                    style="width: 100%; margin-top: 0.5rem; text-align: center; display: block;">Annuler</a>
                            </c:if>
                        </form>
                    </div>
                </div>
            </div>
        </body>

        </html>