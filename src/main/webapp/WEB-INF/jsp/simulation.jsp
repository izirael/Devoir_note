<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>GradeV2 - Simulation</title>
    <link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
    <jsp:include page="common/navbar.jsp" />

    <div class="container" style="max-width: 900px;">
        <div class="glass-card" style="margin-bottom: 2rem;">
            <h2>Simulateur de Note Finale</h2>
            <form action="/simulate" method="POST" style="display: grid; grid-template-columns: 1fr 1fr auto; gap: 1rem; align-items: end;">
                <div class="form-group" style="margin:0;">
                    <label>Candidat</label>
                    <select name="idCandidat" required>
                        <c:forEach items="${candidats}" var="c">
                            <option value="${c.id}" ${c.id == selectedCandId ? 'selected' : ''}>${c.nom} ${c.prenom}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group" style="margin:0;">
                    <label>Matiere</label>
                    <select name="idMatiere" required>
                        <c:forEach items="${matieres}" var="m">
                            <option value="${m.idMatiere}" ${m.idMatiere == selectedMatId ? 'selected' : ''}>${m.nom}</option>
                        </c:forEach>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Lancer la Simulation</button>
            </form>
        </div>

        <c:if test="${not empty result}">
            <div class="glass-card" style="border-left: 4px solid var(--primary);">
                <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 2rem;">
                    <div>
                        <h3 style="margin:0; color: var(--text-secondary); text-transform: uppercase; font-size: 0.8rem; letter-spacing: 0.1em;">Resultat pour ${result.candidat.nom}</h3>
                        <h2 style="margin:0;">${result.matiere.nom}</h2>
                    </div>
                    <div style="text-align: right;">
                        <span style="display:block; font-size: 0.8rem; color: var(--text-secondary);">Note Finale</span>
                        <span style="font-size: 2.5rem; font-weight: 800; color: var(--primary);">${result.finalGrade}</span>
                    </div>
                </div>

                <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 2rem; margin-bottom: 2rem;">
                    <div style="background: rgba(255,255,255,0.03); padding: 1.5rem; border-radius: 1rem; border: 1px solid var(--glass-border);">
                        <label style="margin-bottom: 0.5rem; display: block;">Somme des ecarts (Total Gap)</label>
                        <div style="font-size: 1.5rem; font-weight: 700;">${result.gap}</div>
                        <small style="color: var(--text-secondary);">Calcule sur ${result.notes.size()} corrections</small>
                    </div>
                    <div style="background: rgba(255,255,255,0.03); padding: 1.5rem; border-radius: 1rem; border: 1px solid var(--glass-border);">
                        <label style="margin-bottom: 0.5rem; display: block;">Regle appliquee</label>
                        <c:choose>
                            <c:when test="${result.usedDefault}">
                                <div style="font-size: 1.25rem; font-weight: 700; color: var(--warning);">Defaut (AVG)</div>
                                <small style="color: var(--text-secondary);">Aucun bareme ne correspond au gap</small>
                            </c:when>
                            <c:otherwise>
                                <div style="font-size: 1.25rem; font-weight: 700; color: var(--success);">${result.matchedParam.resolution.nom}</div>
                                <small style="color: var(--text-secondary);">Seuil gap: ${result.matchedParam.gap}</small>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>

                <h2 style="margin: 0;">Note Finale Simulée :
                    <span class="grade ${result.finalGrade < 10 ? 'grade-red' : (result.finalGrade <= 12 ? 'grade-yellow' : 'grade-green')}">
                        <fmt:formatNumber value="${result.finalGrade}" maxFractionDigits="2" />
                    </span>
                </h2>

                <h4>Detail des notes</h4>
                <div class="table-container">
                    <table style="margin-top:0;">
                        <thead>
                            <tr>
                                <th>Correcteur</th>
                                <th>Valeur</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${result.notes}" var="n">
                                <tr>
                                    <td>${n.correcteur.nom}</td>
                                    <td style="font-weight: 700;">${n.valeurNote}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </c:if>
    </div>
</body>
</html>
