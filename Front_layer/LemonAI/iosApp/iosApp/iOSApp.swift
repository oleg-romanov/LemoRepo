import SwiftUI

@main
struct iOSApp: App {
    
    @StateObject var appState = AppState(hasAuthorized: false)
    
	var body: some Scene {
		WindowGroup {
            if appState.hasAuthorized {
                ContentView()
            } else {
                AuthorizationView()
                    .environmentObject(appState)
            }
		}
	}
}

class AppState: ObservableObject {
    @Published var hasAuthorized: Bool
    
    init(hasAuthorized: Bool) {
        self.hasAuthorized = hasAuthorized
    }
}
