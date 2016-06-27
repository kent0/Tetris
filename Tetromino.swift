import Darwin

enum Tetromino: Int {
    case I = 0, O, T, L, J, S, Z

    func block() -> [[Bool]] {
        switch self {
            case .I:
                return [[false, false, false, false],
                        [false, false, false, false],
                        [ true,  true,  true,  true],
                        [false, false, false, false]]
            case .O:
                return [[true, true],
                        [true, true]]
            case .T:
                return [[false,  true, false],
                        [ true,  true,  true],
                        [false, false, false]]
            case .J:
                return [[ true, false, false],
                        [ true,  true,  true],
                        [false, false, false]]
            case .L:
                return [[false, false,  true],
                        [ true,  true,  true],
                        [false, false, false]]
            case .S:
                return [[false,  true,  true],
                        [ true,  true, false],
                        [false, false, false]]
            case .Z:
                return [[ true,  true, false],
                        [false,  true,  true],
                        [false, false, false]]
        }
    }
}
