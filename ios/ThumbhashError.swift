//
//  ThumbhashError.swift
//  react-native-thumbhash
//
//  Created by 윤종배 on 7/21/24.
//

import Foundation

enum ThumbhashError: Error {
    case invalidThumbhashString(isNil: Bool)
    case decodeError(message: String?)
}
