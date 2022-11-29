import React from 'react';
import { DiversityIconContainer } from '../General/HeaderTemplate/DiversityIconContainer';
import { CloseSiteContainer } from '../General/HeaderTemplate/CloseSiteContainer';
import '../../styles/component-styles/information/information.scss';

export const Information = () => (
    <div className="Information">
        <CloseSiteContainer />
        <DiversityIconContainer title="INFORMATIONEN" />
        <p className="Information-info-text">
            Die Diversity Lunch App ist der Beitrag der BROCKHAUS AG zur Diversity Challenge 2021 des
            Charta der Vielfalt e. V.
            <br />
            <br />
            Ziel der App ist es, den Austausch unter den Mitarbeiter*innen zu fördern. Dabei liegt der
            Fokus vor allem auf dem Austausch zwischen jenen Teammitgliedern, die sich in ihren
            Ausprägungen innerhalb der verschiedenen Diversity-Dimensionen besonders stark
            unterscheiden. Warum? Bekanntermaßen neigen wir dazu, Menschen einer anderen Gruppe
            bestimmte Eigenschaften zuzuschreiben. Es entsteht ein Schubladendenken. Lernen wir
            diese Menschen jedoch genauer kennen und tauschen uns mit ihnen aus, bauen wir Vorurteile
            ab und erweitern unseren eigenen Horizont.
            <br />
            <br />
            Mit der Nutzung der App möchten wir dazu anregen, vor allem über die
            Diversity-Dimensionen zu sprechen, in denen Menschen sich unterscheiden. Daher wird mit
            jeder Diversity-Lunch-Einladung auch ein Gesprächsthema vorgeschlagen.
            <br />
            <br />
            <br />
            Viel Spaß bei der Nutzung der App!
            <br />
            <br />
            Das Diversity-Team der BROCKHAUS AG
        </p>

    </div>
);
