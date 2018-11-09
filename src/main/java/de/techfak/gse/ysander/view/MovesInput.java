package de.techfak.gse.ysander.view;


import java.util.List;

import de.techfak.gse.ysander.model.Move;
import de.techfak.gse.ysander.model.error.InvalidMoveException;

public interface MovesInput {

    List<Move> requestMoves() throws InvalidMoveException;

}
