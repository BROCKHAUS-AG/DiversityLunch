package de.brockhausag.diversitylunchspringboot.match.records;

import de.brockhausag.diversitylunchspringboot.dimensions.dimensionCategory.DimensionCategory;
import de.brockhausag.diversitylunchspringboot.meeting.model.MeetingProposalEntity;

public record Match(MeetingProposalEntity proposalOne,
                    MeetingProposalEntity proposalTwo,
                    int score, DimensionCategory category) {

}
