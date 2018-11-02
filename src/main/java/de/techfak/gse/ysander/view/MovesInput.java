package de.techfak.gse.ysander.view;


import de.techfak.gse.ysander.model.Move;
import de.techfak.gse.ysander.model.error.InvalidMoveException;

import java.util.List;

public interface MovesInput {

    List<Move> requestMoves() throws InvalidMoveException;

}
