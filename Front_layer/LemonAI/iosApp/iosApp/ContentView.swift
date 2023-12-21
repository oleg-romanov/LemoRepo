import SwiftUI
import shared

struct ContentView: View {
    @State private var isLoggedIn = true

    @State private var isRegistrationActive = true

	var body: some View {
        // TODO: Get isLoggedIn from Userdefaults logic
        if isLoggedIn {
            // TODO: Go to tabbar logic
            TabView {
                ScannerView()
                    .tabItem {
                        Image(systemName: "camera")
                        Text("Сканирование")
                    }
                ScanHistoryView()
                    .tabItem {
                        Image(systemName: "list.bullet")
                        Text("История")
                    }
                ProfileView()
                    .tabItem {
                        Image(systemName: "person")
                        Text("Профиль")
                    }
            }
        } else {
            AuthFlowView(isRegistrationActive: $isRegistrationActive)
        }
	}
}

struct AuthFlowView: View {
    @Binding var isRegistrationActive: Bool
    
    var body: some View {
        VStack {
            if isRegistrationActive {
                RegistrationView(isRegistrationActive: $isRegistrationActive)
            } else {
                AuthorizationView(isRegistrationActive: $isRegistrationActive)
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
