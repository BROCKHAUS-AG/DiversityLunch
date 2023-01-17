import React from 'react';
import './faq.scss';

const Faq = () => (
    <div className="FAQ">
        <h2>FAQ</h2>
        <details className="FAQ-expandable">
            <summary className="FAQ-title">
                Wie funktioniert die Diversity Lunch App?
            </summary>
            <p className="FAQ-text">
                Fülle im ersten Schritt dein persönliches Profil möglichst vollständig aus. Buche anschließend mind.
                einen Termin innerhalb der nächsten 2 Wochen, an dem du gerne gemeinsam mit einem Kollegen oder
                einer Kollegin zu Mittag essen möchtest.
                Die App berechnet anschließend, welches Teammitglied – mit gleichem verfügbaren Zeitslot – zu dir
                passt bzw. von welchem Teammitglied du dich besonders unterscheidest. Nach einem erfolgreichen Match
                wird euch ein gemeinsamer Termin zugesendet.
                Parallel zum Terminvorschlag versendet die App eine Themenidee für das Gespräch.
                Die Frage beziehungsweise das Gesprächsthema bezieht sich auf eine Diversity-Dimension, in der die
                Angaben unterschiedlich waren.
            </p>
        </details>
        <details className="FAQ-expandable">
            <summary className="FAQ-title">
                Wie wähle ich einen Termin für das gemeinsame Mittagessen aus?
            </summary>
            <p className="FAQ-text">
                Über den Bereich &quot;Termin wählen&quot; kannst du Termine innerhalb der nächsten 2 Wochen buchen.
            </p>
        </details>
        <details className="FAQ-expandable">
            <summary className="FAQ-title">
                Wie finde ich eine*n Lunch-Partner*in?
            </summary>
            <p className="FAQ-text">
                Unser Diversity Lunch App weist dir eine*n Lunch-Partner*in zu.
                Der Algorithmus verbindet zwei Teilnehmende anhand ihrer Interessen und Merkmale.
                Zusammengeführt werden jedoch Personen, die sich in ihren Angaben unterscheiden und nicht jene,
                die vieles gemeinsam haben. Je weniger Merkmale mit einem anderen Teilnehmenden übereinstimmen,
                umso höher ist die Wahrscheinlichkeit eines gemeinsamen Mittagessens.
            </p>
        </details>
        <details className="FAQ-expandable">
            <summary className="FAQ-title">
                Wie kann ich ein Termin absagen?
            </summary>
            <p className="FAQ-text">
                Ein Termin kann über die App sowie in Teams abgesagt werden.
                In beiden Fällen werdet ihr informiert und der Termin aus euren Kalendern gelöscht.
            </p>
        </details>
        <details className="FAQ-expandable">
            <summary className="FAQ-title">
                Einige Fragen sind mir zu heikel, was kann ich tun?
            </summary>
            <p className="FAQ-text">
                Bei jeder Frage gibt es die Möglichkeit &quot;Keine Angabe&quot; auszuwählen.
                Diese Frage wird dann im Matching nicht berücksichtigt und ihr
                bekommt keine Diskussionsfragen aus diesem Bereich.
            </p>
        </details>
        <details className="FAQ-expandable">
            <summary className="FAQ-title">
                Wem kann ich Feedback senden?
            </summary>
            <p className="FAQ-text">
                Feedback und Bugs kannst du uns per Mail an
                {' '}
                <a href="mailto:diversitylunch@brockhaus-ag.de">diversitylunch@brockhaus-ag.de</a>
                {' '}
                senden.
            </p>
        </details>
    </div>
);

export default Faq;
