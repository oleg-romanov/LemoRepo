import SwiftUI
import shared

struct ContentView: View {
    @State private var isLoggedIn = false

    @State private var isRegistrationActive = true

	var body: some View {
        // TODO: Get isLoggedIn from Userdefaults logic
        if isLoggedIn {
            // TODO: Go to tabbar logic
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
