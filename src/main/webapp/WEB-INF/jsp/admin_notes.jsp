<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>GradeV2 - Gestion Notes</title>
    <link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
    <jsp:include page="common/navbar.jsp" />

    <div class="container">
        <div style="display: grid; grid-template-columns: 2fr 1fr; gap: 2rem;">
            <div class="glass-card">
                <h2>Notes Brutes</h2>
                <div class="table-container">
                    <table>
                        <thead>
                            <tr>
                                <th>Candidat</th>
                                <th>Matiere</th>
                                <th>Correcteur</th>
                                <th>Note</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${notes}" var="n">
                                <tr>
                                    <td style="font-weight: 600;">${n.candidat.nom}</td>
                                    <td>${n.matiere.nom}</td>
                                    <td><span class="badge blue">${n.correcteur.nom}</span></td>
                                    <td><span style="font-weight: 700;">${n.valeurNote}</span></td>
                                    <td style="display: flex; gap: 0.5rem;">
                                        <a href="/admin/notes/edit/${n.id}" class="btn btn-outline" style="padding: 0.4rem 0.8rem; font-size: 0.75rem; color: var(--accent);">Modifier</a>
                                        <a href="/admin/notes/delete/${n.id}" class="btn btn-outline" style="padding: 0.4rem 0.8rem; font-size: 0.75rem; color: var(--danger);">Supprimer</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="glass-card">
                <h2>${editNote != null ? 'Modifier' : 'Saisir'} une Note</h2>
                <form action="/admin/notes/save" method="POST">
                    <input type="hidden" name="id" value="${editNote.id}">
                    <div class="form-group">
                        <label>Candidat</label>
                        <select name="candidat.id" required>
                            <c:forEach items="${candidats}" var="c">
                                <option value="${c.id}" ${editNote.candidat.id == c.id ? 'selected' : ''}>${c.nom} ${c.prenom}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Matiere</label>
                        <select name="matiere.idMatiere" required>
                            <c:forEach items="${matieres}" var="m">
                                <option value="${m.idMatiere}" ${editNote.matiere.idMatiere == m.idMatiere ? 'selected' : ''}>${m.nom}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Correcteur</label>
                        <select name="correcteur.id" required>
                            <c:forEach items="${correcteurs}" var="cor">
                                <option value="${cor.id}" ${editNote.correcteur.id == cor.id ? 'selected' : ''}>${cor.nom}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label>Valeur Note</label>
                        <input type="number" step="0.01" name="valeurNote" value="${editNote.valeurNote}" required>
                    </div>
                    <button type="submit" class="btn btn-primary" style="width: 100%;">
                        ${editNote != null ? 'Mettre a jour' : 'Enregistrer la Note'}
                    </button>
                    <c:if test="${editNote != null}">
                        <a href="/admin/notes" class="btn btn-outline" style="width: 100%; margin-top: 0.5rem; text-align: center; display: block;">Annuler</a>
                    </c:if>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
