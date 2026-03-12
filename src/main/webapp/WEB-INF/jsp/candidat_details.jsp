<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Details - ${candidat.nom}</title>
    <link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
    <jsp:include page="common/navbar.jsp" />

    <div class="container">
        <div style="display: flex; align-items: center; gap: 1rem; margin-bottom: 2rem;">
            <a href="/" class="btn btn-outline" style="padding: 0.5rem 1rem;">← Retour</a>
            <h1 style="margin: 0; background: none; -webkit-text-fill-color: var(--text-primary);">Candidat: ${candidat.nom} ${candidat.prenom}</h1>
            <span class="badge blue">${candidat.matricule}</span>
        </div>

        <div style="display: grid; grid-template-columns: 2fr 1fr; gap: 2rem;">
            <div class="glass-card">
                <h2>Notes Calculees</h2>
                <div class="table-container">
                    <table>
                        <thead>
                            <tr>
                                <th>Matiere</th>
                                <th>Note Finale</th>
                                <th>Coefficient</th>
                                <th>Statut</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${matieres}" var="m">
                                <tr>
                                    <td>${m.nom}</td>
                                    <td>
                                        <div class="grade ${calculatedGrades[m] < 10 ? 'badge-red' : 'badge-green'}">
                                            ${calculatedGrades[m] != null ? calculatedGrades[m] : '-'}
                                        </div>
                                    </td>
                                    <td>&times; ${m.coefficient}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${calculatedGrades[m] >= 10}"><span class="badge green">Admis</span></c:when>
                                            <c:when test="${calculatedGrades[m] != null}"><span class="badge red">Ajourne</span></c:when>
                                            <c:otherwise><span class="badge grey">N/A</span></c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

            <div class="glass-card">
                <h3>Notes Brutes</h3>
                <div class="table-container">
                    <table style="font-size: 0.85rem;">
                        <thead>
                            <tr>
                                <th>Matiere</th>
                                <th>Correcteur</th>
                                <th>Note</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${rawNotes}" var="n">
                                <tr>
                                    <td>${n.matiere.nom}</td>
                                    <td style="color: var(--text-secondary);">${n.correcteur.nom}</td>
                                    <td style="font-weight: 700;">${n.valeurNote}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
