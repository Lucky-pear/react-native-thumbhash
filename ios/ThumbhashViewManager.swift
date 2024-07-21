//
//  ThumbhashViewManager.swift
//  react-native-thumbhash
//
//  Created by 윤종배 on 7/21/24.
//

import Foundation
import UIKit

final class ThumbhashViewWrapper: UIView, ThumbhashViewDelegate {
    private var thumbhashView: ThumbhashView

    @objc var thumbhash: NSString? {
        get {
            thumbhashView.thumbhash as NSString?
        }
        set {
            thumbhashView.thumbhash = newValue as String?
        }
    }

    @objc var onLoadStart: RCTDirectEventBlock?
    @objc var onLoadEnd: RCTDirectEventBlock?
    @objc var onLoadError: RCTDirectEventBlock?

    override public init(frame: CGRect) {
        thumbhashView = ThumbhashView(frame: frame)

        super.init(frame: frame)

        thumbhashView.delegate = self
        addSubview(thumbhashView)
    }

    @available(*, unavailable)
    required init?(coder _: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    override func reactSetFrame(_ frame: CGRect) {
        thumbhashView.frame = frame
    }

    override func didSetProps(_: [String]!) {
        thumbhashView.finalizeUpdates()
    }

    func thumbhashViewLoadDidStart() {
        onLoadStart?(nil)
    }

    func thumbhashViewLoadDidEnd() {
        onLoadEnd?(nil)
    }

    func thumbhashViewLoadDidError(_ message: String?) {
        onLoadError?(["message": message as Any])
    }
}

@objc(ThumbhashViewManager)
final class ThumbhashViewManager: RCTViewManager {
    override final func view() -> UIView! {
        return ThumbhashViewWrapper()
    }


    override static func requiresMainQueueSetup() -> Bool {
        return true
    }
}
