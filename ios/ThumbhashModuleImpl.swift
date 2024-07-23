//
//  ThumbhashModuleImpl.swift
//  react-native-thumbhash
//
//  Created by 윤종배 on 7/23/24.
//

import Foundation
import UIKit

@objc
public final class ThumbhashModuleImpl: NSObject {
    private var moduleRegistry: RCTModuleRegistry

    @objc
    public init(moduleRegistry: RCTModuleRegistry) {
        self.moduleRegistry = moduleRegistry
    }

    @objc
    public func encode(_ imageUri: String, resolver resolve: @escaping (String) -> Void, rejecter reject: @escaping (String, String, Error?) -> Void) {
        let formattedUri = imageUri.trimmingCharacters(in: .whitespacesAndNewlines)

        DispatchQueue.global(qos: .utility).async {
            guard let url = URL(string: formattedUri as String) else {
                reject("INVALID_URI", "URI was null!", nil)
                return
            }

            guard let module = self.moduleRegistry.module(forName: "ImageLoader") as? RCTImageLoader else {
                reject("MODULE_NOT_FOUND", "Could not find RCTImageLoader module!", nil)
                return
            }

            module.loadImage(with: URLRequest(url: url), callback: { e, image in
                if e != nil {
                    reject("LOAD_ERROR", "Failed to load URI!", e)
                    return
                }
                guard let image = image else {
                    reject("NULL_IMAGE", "No error was thrown but Image was null.", nil)
                    return
                }
                let thumbhashData = imageToThumbHash(image: image)
                let thumbhash = thumbhashData.base64EncodedString()
                if thumbhash.isEmpty {
                    reject("ENCODE_FAILED", "Failed to encode the image.", nil)
                    return
                }
                resolve(thumbhash)
            })
        }
    }
}
