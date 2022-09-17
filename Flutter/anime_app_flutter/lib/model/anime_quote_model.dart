import 'package:json_annotation/json_annotation.dart';

part 'anime_quote_model.g.dart';

@JsonSerializable()
class AnimeQuoteModel {
  String? anime;
  String? character;
  String? quote;

  AnimeQuoteModel({this.anime, this.character, this.quote});

  factory AnimeQuoteModel.fromJson(Map<String, dynamic> json) =>
      _$AnimeQuoteModelFromJson(json);
  Map<String, dynamic> toJson() => _$AnimeQuoteModelToJson(this);
}
// AnimeQuoteModel.fromJson(Map<String, dynamic> json) {
//   anime = json['anime'];
//   character = json['character'];
//   quote = json['quote'];
// }

// Map<String, dynamic> toJson() {
//   final Map<String, dynamic> data = <String, dynamic>{};
//   data['anime'] = anime;
//   data['character'] = character;
//   data['quote'] = quote;
//   return data;
// }
