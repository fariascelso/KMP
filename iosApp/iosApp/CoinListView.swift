//
//  CoinListView.swift
//  iosApp
//
//  Created by Celso Oliveira Farias on 14/02/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI
import shared

struct CoinListView: View {
    @Binding var coinListUIState: CoinListViewModel.CoinListUIState
    let onViewAppear: () -> Void

    var body: some View {
        NavigationStack {
            ZStack {
                switch coinListUIState {
                case .loading:
                    ProgressView("Carregando...")
                case .success(let coins):
                    List(coins, id: \.id) { coin in
                        VStack(alignment: .leading) {
                            Text("#\(coin.rank)")
                            Text(coin.name)
                            Text(coin.symbol)
                        }

                    }
                case .error:
                    Text("Ocorreu um erro")
                        .foregroundColor(.red)
                }
            }
            .onAppear {
                onViewAppear()
            }
            .navigationTitle("Coin List")
        }
    }
}

struct CoinListView_Previews: PreviewProvider {
    static var previews: some View {
        Group {
            CoinListView(
                coinListUIState: .constant(.loading),
                onViewAppear: {}
            ).previewDisplayName("Loading")

            CoinListView(
                coinListUIState: .constant(.success([CoinKt.coin1, CoinKt.coin2, CoinKt.coin3])),
                onViewAppear: {}
            ).previewDisplayName("Success")

            CoinListView(
                coinListUIState: .constant(.error),
                onViewAppear: {}
            ).previewDisplayName("Error")
        }
    }
}
