<%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <title>GradeV2 - Résolutions</title>
            <link rel="stylesheet" href="/static/css/style.css">
        </head>

        <body>
            <jsp:include page="common/navbar.jsp" />

            <div class="container">
                <div style="display: grid; grid-template-columns: 2fr 1fr; gap: 2rem;">
                    <div class="glass-card">
                        <h2>Stratégies de Résolution</h2>
                        <div class="table-container">
                            <table>
                                <thead>
                                    <tr>
                                        <th>Nom</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${resolutions}" var="r">
                                        <tr>
                                            <td style="font-weight: 600;">${r.nom}</td>
                                            <td style="display: flex; gap: 0.5rem;">
                                                <a href="/admin/resolutions/edit/${r.id}" class="btn btn-outline"
                                                    style="padding: 0.4rem 0.8rem; font-size: 0.75rem; color: var(--accent);">Modifier</a>
                                                <a href="/admin/resolutions/delete/${r.id}" class="btn btn-outline"
                                                    style="padding: 0.4rem 0.8rem; font-size: 0.75rem; color: var(--danger);">Supprimer</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>

                    <div class="glass-card">
                        <h2>${editResolution != null ? 'Modifier' : 'Nouvelle'} Résolution</h2>
                        <form action="/admin/resolutions/save" method="POST">
                            <input type="hidden" name="id" value="${editResolution.id}">
                            <div class="form-group">
                                <label>Nom de la résolution</label>
                                <input type="text" name="nom" placeholder="Ex: AVG" value="${editResolution.nom}"
                                    required>
                            </div>
                            <button type="submit" class="btn btn-primary" style="width: 100%;">${editResolution != null
                                ? 'Mettre à jour' : 'Enregistrer'}</button>
                            <c:if test="${editResolution != null}">
                                <a href="/admin/resolutions" class="btn btn-outline"
                                    style="width: 100%; margin-top: 0.5rem; text-align: center; display: block;">Annuler</a>
                            </c:if>
                        </form>
                        <div style="margin-top: 2rem; font-size: 0.8rem; color: var(--text-secondary);">
                            <p>💡 Ces résolutions correspondent aux opérations lors du calcul des notes (ex. AVG, MIN,
                                MAX).</p>
                        </div>
                    </div>
                </div>
            </div>
        </body>

        </html>