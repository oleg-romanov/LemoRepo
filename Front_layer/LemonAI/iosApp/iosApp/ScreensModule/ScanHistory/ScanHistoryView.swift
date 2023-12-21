//
//  ScanHistoryView.swift
//  iosApp
//
//  Created by Олег Романов on 21.12.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct ScanHistoryView: View {
    
    @State private var displayMode: DisplayMode = .list
    @State private var sortOption: SortOption = .byDate
    
    var body: some View {
        NavigationView {
            ZStack {
                Color(.systemGroupedBackground)
                VStack {
                    Picker("Отобразить как", selection: $displayMode) {
                        Text("Список").tag(DisplayMode.list)
                        Text("Плитки").tag(DisplayMode.tiles)
                    }
                    .pickerStyle(SegmentedPickerStyle())
                    .padding()
                    
                    HStack {
                        Text("Сортировать по")
                        Picker("", selection: $sortOption) {
                            ForEach(SortOption.allCases, id: \.self) { option in
                                Text(option.rawValue).tag(option)
                            }
                        }
                        .pickerStyle(.menu)
                    }
                    .frame(maxWidth: .infinity)
                    .frame(height: 40)
                    .background(.white)
                    .clipShape(RoundedRectangle(cornerRadius: 8, style: .continuous))
                    .padding(.horizontal, 16)
                    
                    let scans = obtainScanHistory()
                    
                    if displayMode == .list {
                        ScanListView(scans: scans)
                    } else {
                        ScanTilesView(scans: scans)
                    }
                }
                .navigationTitle(Text("История сканирований"))
                .navigationBarTitleDisplayMode(.inline)
            }
        }
    }
    
    private func obtainScanHistory() -> [Scan] {
        // TODO: obtain scans from network logic
        var scans = [
            Scan(
                date: Date(),
                storeName: "Магнит",
                result: "Плохой"
            ),
            Scan(
                date: Date(),
                storeName: "Пятерочка",
                result: "Хороший"
            ),
            Scan(
                date: Date(),
                storeName: "Магнит",
                result: "Хороший"
            ),
            Scan(
                date: Date(),
                storeName: "Магнит",
                result: "Плохой"
            ),
            Scan(
                date: Date(),
                storeName: "Пятерочка",
                result: "Плохой"
            )
        ]
        switch sortOption {
            case .byDate:
                return scans.sorted { $0.date < $1.date }
            case .byStoreName:
                return scans.sorted { $0.storeName < $1.storeName }
            case .byResult:
                return scans.sorted { $0.result < $1.result }
        }
    }
}

struct ScanListView: View {
    
    var scans: [Scan]
    
    var body: some View {
        List(scans) { scan in
            ScanRow(scan: scan)
        }.padding(.vertical, 0)
    }
}

struct ScanTilesView: View {
    
    var scans: [Scan]
    
    var body: some View {
        ScrollView {
            LazyVGrid(columns: [GridItem(), GridItem(), GridItem()]) {
                ForEach(scans) { scan in
                    ScanTile(scan: scan)
                }
            }
            .padding()
        }
    }
}

enum DisplayMode {
    case list
    case tiles
}

enum SortOption: String, CaseIterable {
    case byDate = "Дате"
    case byStoreName = "Названию магазина"
    case byResult = "Результату"
}
