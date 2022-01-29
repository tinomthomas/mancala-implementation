export interface Kalah {
    sessionId: string;
    board: Board;
    currentPlayer: Player;
    otherPlayer: Player;
    winner: Player;
}

export type Board = Readonly<{
    pits: Pit[];
}>;

export type Pit = Readonly<{
    index: number;
    store: boolean;
    seeds: Seed[];
    player: Player;
    seedWrappers: SeedWrapper[];
}>;

export type Seed = Readonly<{
    type: string;
}>;

export type Player = Readonly<{
    firstName: string;
    lastName: string;
    userName: string;
    position: string;
}>;

export interface PlayerContext {
    player: Player;
    houses: Pit[];
    store: Pit;
    current: boolean;
}

export type HouseSelectionRequest = Readonly<{
    sessionId: string;
    house: Pit;
}>;

export type WinnerDialogData = Readonly<{
    winner: Player;
    topPlayerContext: PlayerContext;
    bottomPlayerContext: PlayerContext;
}>;

export type SeedWrapper = Readonly<{
    seeds: Seed[];
    degree: string;
}>;