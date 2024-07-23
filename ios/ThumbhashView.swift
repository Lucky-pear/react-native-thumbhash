//
//  ThumbhashView.swift
//  react-native-thumbhash
//
//  Created by 윤종배 on 7/21/24.
//


import Foundation
import UIKit

@objc
public protocol ThumbhashViewDelegate {
    func thumbhashViewLoadDidStart()
    func thumbhashViewLoadDidEnd()
    func thumbhashViewLoadDidError(_ message: String?)
}

public final class ThumbhashView: UIView {
    @objc public var thumbhash: String?
    @objc public var decodeAsync: Bool = false
    @objc override public var contentMode: ContentMode {
        get {
            imageContainer.contentMode
        }
        set {
            imageContainer.contentMode = newValue
        }
    }

    @objc public weak var delegate: ThumbhashViewDelegate?

    private var lastState: ThumbhashCache?
    private let imageContainer: UIImageView

    override public init(frame: CGRect) {
        imageContainer = UIImageView()
        imageContainer.autoresizingMask = [.flexibleWidth, .flexibleHeight]
        imageContainer.clipsToBounds = true
        imageContainer.contentMode = .scaleAspectFill
        super.init(frame: frame)
        addSubview(imageContainer)
    }

    @available(*, unavailable)
    required init?(coder _: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    private final func renderThumbhash() {
        do {
            emitLoadStartEvent()
            guard let thumbhash = thumbhash else {
                throw ThumbhashError.invalidThumbhashString(isNil: true)
            }
            guard let data = Data(base64Encoded: thumbhash) else {
                throw ThumbhashError.invalidThumbhashString(isNil: false)
            }
            
            let image = thumbHashToImage(hash: data)
            setImageContainerImage(image: image)
            emitLoadEndEvent()
            return;
        } catch let ThumbhashError.decodeError(message) {
            emitLoadErrorEvent(message: message)
        } catch let ThumbhashError.invalidThumbhashString(isNil) {
            emitLoadErrorEvent(message: isNil ? "The provided thumbhash string must not be null!" : "The provided thumbhash string was invalid.")
        } catch {
            emitLoadErrorEvent(message: "An unknown error occured while trying to decode the thumbhash!")
        }
        setImageContainerImage(image: nil)
    }

    @objc
    public final func finalizeUpdates() {
        let shouldReRender = self.shouldReRender()
        if shouldReRender {
            if decodeAsync {
                DispatchQueue.global(qos: .userInteractive).async {
                    self.renderThumbhash()
                }
            } else {
                renderThumbhash()
            }
        }
    }

    private final func shouldReRender() -> Bool {
        defer {
            self.lastState = ThumbhashCache(thumbhash: self.thumbhash)
        }
        guard let lastState = lastState else {
            return true
        }
        guard let thumbhash = thumbhash else {
            return true
        }
        return lastState.isDifferent(thumbhash: thumbhash)
    }

    private final func setImageContainerImage(image: UIImage?) {
        if Thread.isMainThread {
            imageContainer.image = image
        } else {
            DispatchQueue.main.async {
                self.imageContainer.image = image
            }
        }
    }

    private final func emitLoadErrorEvent(message: String?) {
        if let message = message {
            RCTDefaultLogFunction(.error, RCTLogSource.native, #file, #line as NSNumber, "ThumbhashView: \(message)")
        }
        delegate?.thumbhashViewLoadDidError(message)
    }

    private final func emitLoadStartEvent() {
        delegate?.thumbhashViewLoadDidStart()
    }

    private final func emitLoadEndEvent() {
        delegate?.thumbhashViewLoadDidEnd()
    }
}
