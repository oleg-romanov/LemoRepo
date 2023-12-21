//
//  ScanTile.swift
//  iosApp
//
//  Created by Олег Романов on 21.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct ScanTile: View {
    var scan: Scan

    var body: some View {
        VStack(alignment: .leading) {
            Text("Дата: \(formattedDate)")
                .font(.subheadline)
                .foregroundStyle(.secondary)
                .padding(.bottom, 4)
                .minimumScaleFactor(0.5)
            Text("Магазин: \(scan.storeName)")
                .font(.subheadline)
                .padding(.bottom, 4)
                .minimumScaleFactor(0.5)
            Text("Результат: \(scan.result)")
                .font(.headline)
                .foregroundStyle(.primary)
                .padding(.bottom, 4)
                .minimumScaleFactor(0.5)
        }
        .padding(12)
        .background(RoundedRectangle(cornerRadius: 12).fill(.white))
        .overlay(
            RoundedRectangle(cornerRadius: 12)
                .stroke(
                    resultIsGood(result: scan.result) ? .green : .red,
                    lineWidth: 1
                )
                .shadow(color: .black.opacity(0.5), radius: 4, x: 2, y: 2)
        )
    }

    private var formattedDate: String {
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "dd.MM.yyyy"
        return dateFormatter.string(from: scan.date)
    }
    
    private func resultIsGood(result: String) -> Bool {
        return result == "Хороший" ? true : false
    }
}
