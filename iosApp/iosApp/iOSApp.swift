import SwiftUI
import shared

@main
struct iOSApp: App {
    @StateObject private var viewModel = CoinListViewModel(
        coinRepository: CoinRepositoryImpl(
            apiClient: ApiClient(),
            dispatcherProvider: DispatcherProvider_iosKt.getDispatcherProvider()
        )
    )

    var body: some Scene {
        WindowGroup {
            CoinListView(
                coinListUIState: $viewModel.coinListUIState,
                onViewAppear: {
                    viewModel.fetchCoins()
                }
            )
        }
    }
}
