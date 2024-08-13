import UIKit
import SwiftUI
import ComposeApp

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct HideKeyboardModifier: ViewModifier {
    func body(content: Content) -> some View {
        content.toolbar {
            ToolbarItem(placement: .keyboard) {
                Button("Done") {
                    UIApplication.shared.sendAction(
                        #selector(UIResponder.resignFirstResponder),
                        to: nil,
                        from: nil,
                        for: nil
                    )
                }
            }
        }
    }
}

extension View {
    func addHideKeyboardButton() -> some View {
        self.modifier(HideKeyboardModifier())
    }
}

struct ContentView: View {
    
    @State var text: String = ""
    
    var body: some View {
        VStack {
            Text("SwiftUI input")
            TextField("Click to edit", text: $text)
                .padding(32)
                .addHideKeyboardButton()
            
            Divider()
            Text("Compose input")

            ComposeView()
                .ignoresSafeArea(.keyboard)
        }
    }
}



