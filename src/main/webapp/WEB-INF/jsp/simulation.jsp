<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Simulation de Calcul de Note</title>
    <link rel="stylesheet" href="/static/css/style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>Simulation de Grading</h1>
            <nav>
                <a href="/" class="nav-link">Accueil</a>
                <a href="/admin/parametres" class="nav-link">Barèmes</a>
            </nav>
        </header>

        <div class="glass-card" style="max-width: 600px; margin: 0 auto 2rem auto;">
            <h2>Contexte de Simulation</h2>
            <form action="/simulate" method="POST">
                <div class="form-group">
                    <label>Candidat</label>
                    <select name="idCandidat" required>
                        <option value="">-- Choisir un candidat --</option>
                        <c:forEach items="${candidats}" var="cand">
                            <option value="${cand.id}" ${cand.id == selectedCandId ? 'selected' : ''}>
                                ${cand.nom} ${cand.prenom} (${cand.matricule})
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label>Matière</label>
                    <select name="idMatiere" required>
                        <option value="">-- Choisir une matière --</option>
                        <c:forEach items="${matieres}" var="mat">
                            <option value="${mat.idMatiere}" ${mat.idMatiere == selectedMatId ? 'selected' : ''}>
                                ${mat.nom}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <button type="submit" style="width: 100%;">Lancer la Simulation</button>
            </form>
        </div>

        <c:if test="${not empty result}">
            <div class="glass-card animated-fade-in">
                <h2>Résultat de la Simulation</h2>
                
                <div style="display: grid; grid-template-columns: 1fr 1fr; gap: 2rem; margin-top: 1rem;">
                    <div class="info-box">
                        <h3>Données d'Entrée</h3>
                        <p><strong>Candidat :</strong> ${result.candidat.nom} ${result.candidat.prenom}</p>
                        <p><strong>Matière :</strong> ${result.matiere.nom}</p>
                        <p><strong>Notes trouvées :</strong> ${result.notes.size()}</p>
                        <ul>
                            <c:forEach items="${result.notes}" var="n">
                                <li>Correction par ${n.correcteur.nom} : <strong>${n.valeurNote}</strong></li>
                            </c:forEach>
                        </ul>
                    </div>

                    <div class="info-box">
                        <h3>Analyse du Moteur</h3>
                        <c:choose>
                            <c:when test="${result.notes.size() < 2}">
                                <p>Pas assez de notes pour un calcul dynamique. La note finale est la valeur brute ou 0.</p>
                            </c:when>
                            <c:otherwise>
                                <p><strong>Somme des écarts (Total Gap) :</strong> <span class="badge">${result.gap}</span></p>
                                <c:choose>
                                    <c:when test="${not empty result.matchedParam}">
                                        <p><strong>Barème matché :</strong> [${result.matchedParam.min} - ${result.matchedParam.max}]</p>
                                        <p><strong>Règle appliquée :</strong> <span class="badge blue">${result.matchedParam.operateur.symbole} (${result.matchedParam.operateur.nom})</span></p>
                                    </c:when>
                                    <c:otherwise>
                                        <p style="color: var(--warning)">⚠️ Aucun barème ne correspond. Application de la <strong>Moyenne par défaut</strong>.</p>
                                    </c:otherwise>
                                </c:choose>
                            </c:otherwise>
                        </c:choose>
                        
                        <div style="margin-top: 2rem; border-top: 1px solid rgba(255,255,255,0.1); padding-top: 1rem;">
                            <h2 style="margin: 0;">Note Finale Simulée : 
                                <span class="grade ${result.finalGrade < 10 ? 'grade-red' : (result.finalGrade <= 12 ? 'grade-yellow' : 'grade-green')}">
                                    <fmt:formatNumber value="${result.finalGrade}" maxFractionDigits="2" />
                                </span>
                            </h2>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
    </div>
</body>
</html>
