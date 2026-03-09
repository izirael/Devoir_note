<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Grade Management System</title>
    <link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>Student Grades</h1>
            <nav>
                <a href="/config" class="nav-link">Configuration</a>
            </nav>
        </header>

        <div class="glass-card">
            <table>
                <thead>
                    <tr>
                        <th>Candidat</th>
                        <c:forEach items="${matieres}" var="matiere">
                            <th>${matiere.nom}</th>
                        </c:forEach>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${candidats}" var="candidat">
                        <tr>
                            <td>
                                <strong>${candidat.nom} ${candidat.prenom}</strong><br>
                                <small style="color: var(--text-secondary)">${candidat.matricule}</small>
                            </td>
                            <c:forEach items="${matieres}" var="matiere">
                                <c:set var="grade" value="${gradeMap[candidat.id][matiere]}" />
                                <td>
                                    <c:choose>
                                        <c:when test="${not empty grade}">
                                            <span class="grade ${grade < 10 ? 'grade-red' : (grade <= 12 ? 'grade-yellow' : 'grade-green')}">
                                                <fmt:formatNumber value="${grade}" maxFractionDigits="2" />
                                            </span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="grade" style="color: var(--text-secondary)">-</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </c:forEach>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
