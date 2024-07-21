//
//  ThumbhashCache.swift
//  react-native-thumbhash
//
//  Created by 윤종배 on 7/21/24.
//

import Foundation

final class ThumbhashCache {
    var thumbhash: String?

    init(thumbhash: String?) {
        self.thumbhash = thumbhash
    }

    final func isDifferent(thumbhash: String) -> Bool {
        return self.thumbhash != thumbhash
    }
}
