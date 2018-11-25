package de.techfak.gse.ysander.communication.drivers;

import de.techfak.gse.ysander.communication.handlers.MoveHandler;
import de.techfak.gse.ysander.communication.handlers.RawInputHandler;

public interface RawInputDriver extends Driver, RawInputHandler {
    void setMoveHandler(MoveHandler moveHandler);
}
