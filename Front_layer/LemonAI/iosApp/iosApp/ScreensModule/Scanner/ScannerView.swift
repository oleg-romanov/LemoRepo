//
//  ScannerView.swift
//  iosApp
//
//  Created by Олег Романов on 21.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct ScannerView: View {
    @State private var isImagePickerPresented = false
    @State private var capturedImage: UIImage?
    @State private var selectedStore: String?
    @State private var pickerSourceType: UIImagePickerController.SourceType?

    var isSubmitEnabled: Bool {
        selectedStore != nil
    }

    var body: some View {
        VStack {
            if let image = capturedImage {
                Spacer()
                Image(uiImage: image)
                    .resizable()
                    .scaledToFit()
                    .frame(width: 300, height: 300)
            } else {
                Spacer()
                Text("Качество лимона по фотографии")
            }

            HStack {
                Button("Выбрать из галереи") {
                    pickerSourceType = .photoLibrary
                    isImagePickerPresented.toggle()
                }
                .padding()

                Button("Сделать фото лимона") {
                    pickerSourceType = .camera
                    isImagePickerPresented.toggle()
                }
                .padding()
            }

            HStack {
                Text("Магазин:")
                    .padding()

                Picker("Выберите магазин", selection: $selectedStore) {
                    Text("Магнит").tag("Магнит")
                    Text("Пятерочка").tag("Пятерочка")
                    Text("Перекресток").tag("Перекресток")
                    Text("Дикси").tag("Дикси")
                    Text("Ашан").tag("Ашан")
                    Text("Лента").tag("Лента")
                    Text("Метро").tag("Метро")
                }
                .pickerStyle(MenuPickerStyle())
                .padding()
            }

            Spacer()

            Button(action: {
                guard let selectedStore = selectedStore else {
                    // Handle the case where no store is selected
                    return
                }

                guard let image = capturedImage else {
                    // Handle the case where no image is captured
                    return
                }

                // Now you can use 'selectedStore' and 'image' for your server submission logic
                // For example, you can print them:
                print("Selected Store: \(selectedStore)")
                print("Image captured")
                // Assuming you have a function for uploading images to the server, you can call it like this:
                // uploadImage(image: image, store: selectedStore)
            }, label: {
                Text("Отправить")
                    .frame(maxWidth: .infinity)
                    .frame(height: 54)
                    .background(.blue)
                    .foregroundStyle(.white)
                    .clipShape(RoundedRectangle(cornerRadius: 12, style: .continuous))
            })
            .padding(.horizontal, 16)
            .padding(.bottom, 24)
        }
        .sheet(isPresented: $isImagePickerPresented) {
            ImagePicker(image: $capturedImage, sourceType: pickerSourceType ?? .camera)
        }
    }
    
    private func sendToServer() {
        
    }
}

struct ImagePicker: UIViewControllerRepresentable {
    @Binding var image: UIImage?
    var sourceType: UIImagePickerController.SourceType

    class Coordinator: NSObject, UINavigationControllerDelegate, UIImagePickerControllerDelegate {
        var parent: ImagePicker

        init(parent: ImagePicker) {
            self.parent = parent
        }

        func imagePickerController(
            _ picker: UIImagePickerController,
            didFinishPickingMediaWithInfo info: [UIImagePickerController.InfoKey: Any]
        ) {
            if let uiImage = info[.originalImage] as? UIImage {
                parent.image = uiImage
            }

            picker.dismiss(animated: true)
        }

        func imagePickerControllerDidCancel(_ picker: UIImagePickerController) {
            picker.dismiss(animated: true)
        }
    }

    func makeCoordinator() -> Coordinator {
        return Coordinator(parent: self)
    }

    func makeUIViewController(context: Context) -> UIViewController {
        let controller = UIImagePickerController()
        controller.delegate = context.coordinator
        controller.sourceType = sourceType // Use the specified sourceType
        return controller
    }

    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {
        // Update UI if needed
    }
}
