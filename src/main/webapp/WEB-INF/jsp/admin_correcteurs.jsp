<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <title>GradeV2 - Correcteurs</title>
            <link rel="stylesheet" href="/static/css/style.css">
        </head>

        <body>
            <jsp:include page="common/navbar.jsp" />

            <div class="container">
                <div style="display: grid; grid-template-columns: 2fr 1fr; gap: 2rem;">
                    <div class="glass-card">
                        <h2>Correcteurs</h2>
                        <div class="table-container">
                            <table>
                                <thead>
                                    <tr>
                                        <th>Nom</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${correcteurs}" var="c">
                                        <tr>
                                            <td style="font-weight: 600;">${c.nom}</td>
                                            <td style="display: flex; gap: 0.5rem;">
                                                <a href="/admin/correcteurs/edit/${c.id}" class="btn btn-outline"
                                                    style="padding: 0.4rem 0.8rem; font-size: 0.75rem; color: var(--accent);">Modifier</a>
                                                <a href="/admin/correcteurs/delete/${c.id}" class="btn btn-outline"
                                                    style="padding: 0.4rem 0.8rem; font-size: 0.75rem; color: var(--danger);">Supprimer</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>

                    <div class="glass-card">
                        <h2>${editCorrecteur != null ? 'Modifier' : 'Nouveau'} Correcteur</h2>
                        <form action="/admin/correcteurs/save" method="POST">
                            <input type="hidden" name="id" value="${editCorrecteur.id}">
                            <div class="form-group">
                                <label>Nom du correcteur</label>
                                <input type="text" name="nom" placeholder="Ex: conepa" value="${editCorrecteur.nom}"
                                    required>
                            </div>
                            <button type="submit" class="btn btn-primary" style="width: 100%;">${editCorrecteur != null
                                ? 'Mettre a jour' : 'Enregistrer'}</button>
                            <c:if test="${editCorrecteur != null}">
                                <a href="/admin/correcteurs" class="btn btn-outline"
                                    style="width: 100%; margin-top: 0.5rem; text-align: center; display: block;">Annuler</a>
                            </c:if>
                        </form>
                    </div>
                </div>
            </div>
        </body>

        </html>