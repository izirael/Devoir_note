<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Détails Candidat - ${candidat.nom}</title>
    <link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>Détails du Candidat</h1>
            <nav>
                <a href="/" class="nav-link">Retour Liste</a>
            </nav>
        </header>

        <div class="glass-card" style="margin-bottom: 2rem;">
            <h2>Informations Générales</h2>
            <p><strong>Matricule :</strong> ${candidat.matricule}</p>
            <p><strong>Nom Full :</strong> ${candidat.nom} ${candidat.prenom}</p>
        </div>

        <div class="glass-card" style="margin-bottom: 2rem;">
            <h2>Notes Calculées (Barèmes Dynamiques)</h2>
            <table>
                <thead>
                    <tr>
                        <th>Matière</th>
                        <th>Note Finale</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${matieres}" var="m">
                        <c:set var="grade" value="${calculatedGrades[m]}" />
                        <tr>
                            <td>${m.nom}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${not empty grade}">
                                        <span class="grade ${grade < 10 ? 'grade-red' : (grade <= 12 ? 'grade-yellow' : 'grade-green')}">
                                            <fmt:formatNumber value="${grade}" maxFractionDigits="2" />
                                        </span>
                                    </c:when>
                                    <c:otherwise>-</c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="glass-card">
            <h2>Détail des Corrections (Notes Raw)</h2>
            <table>
                <thead>
                    <tr>
                        <th>Matière</th>
                        <th>Correcteur</th>
                        <th>Note</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${rawNotes}" var="n">
                        <tr>
                            <td>${n.matiere.nom}</td>
                            <td>${n.correcteur.nom}</td>
                            <td>${n.valeurNote}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
