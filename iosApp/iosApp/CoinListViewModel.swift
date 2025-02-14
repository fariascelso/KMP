//
//  CoinListViewModel.swift
//  iosApp
//
//  Created by Celso Oliveira Farias on 14/02/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import shared

@MainActor
class CoinListViewModel: ObservableObject {

    @Published var coinListUIState: CoinListUIState = .loading

    private let coinRepository: CoinRepository

    init(coinRepository: CoinRepository) {
        self.coinRepository = coinRepository
    }

    func fetchCoins() {
        Task {
            do {
                let coins = try await coinRepository.getCoins()
                coinListUIState = .success(coins)
            } catch {
                coinListUIState = .error
            }
        }
    }

    enum CoinListUIState {
        case loading
        case success([Coin])
        case error
    }
}
